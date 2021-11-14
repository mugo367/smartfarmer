package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.EmployeeEjbI;
import com.smartfarmer.ejb.interfaces.SalaryRecordEjbI;
import com.smartfarmer.entities.Employee;
import com.smartfarmer.entities.SalaryRecord;
import lombok.SneakyThrows;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(
        name = "EmployeeController",
        urlPatterns = {
                "/add-employee", "/delete-employee", "/edit-employee", "/view-employees", "/view-salaries",
                "/delete-salary", "/add-salary"
        }
)
public class EmployeeActions extends BaseController {
        @EJB
        EmployeeEjbI employeeEjb;

        @EJB
        SalaryRecordEjbI salaryRecordEjb;

        private Employee employee = new Employee();
        private SalaryRecord salaryRecord = new SalaryRecord();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();

                switch (action) {
                        case "/view-employees":
                                transform(employee, request.getParameterMap());
                                handleResponse(response, employeeEjb.list(employee, 0, 0).getList());
                                break;
                        case "/view-salaries":
                                transform(salaryRecord, request.getParameterMap());
                                handleResponse(response, salaryRecordEjb.list(salaryRecord, 0, 0).getList());
                                break;
                }
        }

        @SneakyThrows
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();

                switch (action) {
                        case "/add-employee":
                                transform(employee, request.getParameterMap());
                                employeeEjb.add(employee);
                                handleResponse(response);
                                break;

                        case "/add-salary":
                                transform(salaryRecord, request.getParameterMap());
                                response.getWriter().print(jsonMapper.writeValueAsString(salaryRecordEjb.add(salaryRecord)));
                                break;

                        case "/edit-employee":

                                break;
                }
        }

        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();

                switch (action) {
                        case "/delete-employee":
                                List<String> ids = Arrays.asList(request.getParameter("ids"));
                                for (String id : ids) {
                                        employeeEjb.delete(Long.valueOf(id));
                                }
                                break;
                        case "/delete-salary":
                                List<String> idds = Arrays.asList(request.getParameter("ids"));
                                for (String id : idds) {
                                        salaryRecordEjb.delete(Long.valueOf(id));
                                }
                                break;
                }
        }
}