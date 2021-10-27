package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.EmployeeEjbI;

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

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();
                int id = (Integer) request.getSession().getAttribute("uid");
                jsonMapper.setDateFormat(df);

                if ("/view-employees".equals(action)) {
                        response.setContentType("application/json");
                        response.getWriter().print(jsonMapper.writeValueAsString(employeeEjb.listEmployees(id)));
                }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();
                int id = (Integer) request.getSession().getAttribute("uid");
                switch (action) {
                        case "/add-employee":
                                response.setContentType("application/json");
                                response.getWriter().print(jsonMapper.writeValueAsString(employeeEjb.addEmployee(request.getParameterMap(), id)));
                                break;
                        case "/edit-employee":

                                break;
                }
        }

        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getServletPath();
                int id = (Integer) request.getSession().getAttribute("uid");

                if ("/delete-employee".equals(action)) {
                        employeeEjb.deleteEmployees(request.getParameter("employeeNumbers"), id);
                }
        }
}