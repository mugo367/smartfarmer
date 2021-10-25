package com.smartfarmer.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.smartfarmer.dao.DaoI;
import com.smartfarmer.ejb.EmployeeEjbI;
import com.smartfarmer.model.Employee;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebServlet(
        name = "EmployeeController",
        urlPatterns = {
                "/add-employee", "/delete-employee", "/edit-employee", "/view-employees"
        }
)
public class EmployeeController extends BaseController {
        @Inject
        EmployeeEjbI employeeEjbI;

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();
                int id = (Integer) request.getSession().getAttribute("uid");
                jsonMapper.setDateFormat(df);

                if ("/view-employees".equals(action)) {
                        response.setContentType("application/json");
                        response.getWriter().print(jsonMapper.writeValueAsString(employeeEjbI.listEmployees(id)));
                }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();
                int id = (Integer) request.getSession().getAttribute("uid");
                switch (action) {
                        case "/add-employee":
                                response.setContentType("application/json");
                                response.getWriter().print(jsonMapper.writeValueAsString(employeeEjbI.addEmployee(request.getParameterMap(), id)));
                                break;
                        case "/edit-employee":
                                editEmployee(request, response);
                                break;
                }
        }

        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();
                int id = (Integer) request.getSession().getAttribute("uid");

                if ("/delete-employee".equals(action)) {
                        employeeEjbI.deleteEmployees(request.getParameter("employeeNumbers"), id);
                }
        }
}