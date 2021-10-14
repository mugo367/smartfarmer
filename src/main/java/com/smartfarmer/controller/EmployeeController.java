package com.smartfarmer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.dao.DaoI;
import com.smartfarmer.model.Employee;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.model.enumFiles.Designation;
import com.smartfarmer.model.enumFiles.EmpType;
import com.smartfarmer.model.enumFiles.Gender;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(
        name = "EmployeeController",
        urlPatterns = {
                "/add-employee", "/delete-employee", "/edit-employee", "/view-employees"
        }
)
public class EmployeeController extends BaseController {
        @Inject
        @Named("EmployeeDao")
        DaoI<Employee> employeeDao;

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();
                switch (action){
                        case "/add-employee":
                                addEmployee(request, response);
                                break;
                        case "/delete-employee":
                                deleteEmployee(request, response);
                                break;
                        case "/edit-employee":
                                editEmployee(request, response);
                                break;
                }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();

                if ("/view-employees".equals(action)) {
                        viewEmployees(request, response);
                }
        }

        //add employee details
        private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
                response.setContentType("application/json");
                try {
                        Employee employee = new Employee(
                                request.getParameter("employeeNumber"),
                                request.getParameter("employeeName"),
                                Integer.parseInt(request.getParameter("idNumber")),
                                Gender.valueOf(request.getParameter("employeeGender")),
                                request.getParameter("employeeEmail"),
                                request.getParameter("employeeContact"),
                                request.getParameter("employeeEmergencyContact"),
                                new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("employeeDateOfEmp")),
                                Designation.valueOf(request.getParameter("employeeDesignation")),
                                EmpType.valueOf(request.getParameter("employeeType")),
                                (Integer) request.getSession().getAttribute("uid")

                        );
                        employeeDao.add(employee);
                } catch (ParseException | SQLException e) {
                        resultWrapper.setSuccess(false);
                        resultWrapper.setMessage(e.getMessage());
                }
                response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));

        }

        // delete employee details
        private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
                try {
                        if(employeeDao.delete(request.getParameter("employeeNumber"), Integer.parseInt(request.getParameter("uid")))){
                                response.sendRedirect("./viewEmployees.jsp");
                        }

                        //check on else
                } catch (ParseException | SQLException | IOException e) {
                        e.printStackTrace();
                }
        }

        //edit employee details
        private void editEmployee(HttpServletRequest request, HttpServletResponse response) {

        }
        //view employees
        private void viewEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
                //int id = Integer.parseInt(request.getParameter("uid"));
                List<Employee> employeeList;
                try {
                        employeeList = employeeDao.read(1);
                        ObjectMapper mapper = new ObjectMapper();
                        ResultWrapper wrapper = new ResultWrapper();
                        wrapper.setList(employeeList);
                        response.setContentType("application/json");
                        response.getWriter().print(mapper.writeValueAsString(wrapper));

                } catch (SQLException | ParseException | JsonProcessingException e) {
                        e.printStackTrace();
                }
        }
}
