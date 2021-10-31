package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.FieldDetailEjbI;
import com.smartfarmer.entities.Farmer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "FieldDetailsController",
        urlPatterns = {
                "/add-field", "/delete-field", "/edit-fieldDetails", "/view-fields"
        }
)
public class FieldDetailsActions extends BaseController {
    @EJB
    FieldDetailEjbI fieldDetailEjb;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        Farmer farmerDetails = (Farmer) request.getSession().getAttribute("details");
        switch (action) {
            case ("/add-field"):
                response.setContentType("application/json");
                response.getWriter().print(jsonMapper.writeValueAsString(fieldDetailEjb.addField(request.getParameterMap(), id, farmerDetails)));
                break;
            case ("/editFieldDetails"):

                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        if ("/view-fields".equals(action)) {
            response.setContentType("application/json");
            response.getWriter().print(jsonMapper.writeValueAsString(fieldDetailEjb.listFields(id)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        if ("/delete-field".equals(action)) {
            fieldDetailEjb.deleteField(request.getParameter("fieldLabels"), id);
        }
    }
}
