package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.FieldDetailEjbI;
import com.smartfarmer.entities.Farmer;
import com.smartfarmer.entities.Field;
import lombok.SneakyThrows;

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

    private Field field = new Field();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        Farmer farmerDetails = (Farmer) request.getSession().getAttribute("details");
        switch (action) {
            case ("/add-field"):
                transform(field, request.getParameterMap());
                fieldDetailEjb.addField(field);

                handleResponse(response);
                break;
            case ("/editFieldDetails"):

                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/view-fields".equals(action)) {
            transform(field, request.getParameterMap());
            handleResponse(response, fieldDetailEjb.listFields(field, 0, 0).getList());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/delete-field".equals(action)) {

        }
    }
}
