package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.EmployeeEjbI;
import com.smartfarmer.entities.Employee;
import lombok.SneakyThrows;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "EmployeeController",
        urlPatterns = {
                "/add-employee", "/delete-employee", "/edit-employee", "/view-employees"
        }
)
public class EmployeeActions extends BaseController {
        @EJB
        EmployeeEjbI employeeEjb;

        private Employee employee = new Employee();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();

                if ("/view-employees".equals(action)) {
                        transform(employee, request.getParameterMap());
                        handleResponse(response, employeeEjb.listEmployees(employee, 0, 0).getList());
                }
        }

        @SneakyThrows
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();

                switch (action) {
                        case "/add-employee":
                                transform(employee, request.getParameterMap());
                                employeeEjb.addEmployee(employee);
                                handleResponse(response);
                                break;
                        case "/edit-employee":

                                break;
                }
        }

        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();

                if ("/delete-employee".equals(action)) {

                }
        }
}