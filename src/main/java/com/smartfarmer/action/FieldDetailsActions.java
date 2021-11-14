package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.FieldDetailEjbI;
import com.smartfarmer.ejb.interfaces.FieldProductionEjbI;
import com.smartfarmer.entities.Field;
import com.smartfarmer.entities.FieldProduction;
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
        name = "FieldDetailsController",
        urlPatterns = {
                "/add-field", "/delete-field", "/edit-fieldDetails", "/view-fields",
                "/view-fieldProduction", "/add-fieldProduction", "/delete-fieldProduction"
        }
)
public class FieldDetailsActions extends BaseController {
    @EJB
    FieldDetailEjbI fieldDetailEjb;

    @EJB
    FieldProductionEjbI fieldProductionEjb;


    private Field field = new Field();
    private FieldProduction fieldProduction = new FieldProduction();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case ("/add-field"):
                transform(field, request.getParameterMap());
                response.getWriter().print(jsonMapper.writeValueAsString(fieldDetailEjb.add(field)));
                break;
            case ("/add-fieldProduction"):
                transform(fieldProduction, request.getParameterMap());
                fieldProductionEjb.add(fieldProduction);
                handleResponse(response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/view-fields":
                transform(field, request.getParameterMap());
                handleResponse(response, fieldDetailEjb.list(field, 0, 0).getList());
                break;
            case "/view-fieldProduction":
                transform(fieldProduction, request.getParameterMap());
                handleResponse(response, fieldProductionEjb.list(fieldProduction, 0, 0).getList());
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/delete-field":
                List<String> ids = Arrays.asList(request.getParameter("ids"));
                for (String id : ids) {
                    fieldDetailEjb.delete(Long.valueOf(id));
                }
                break;
            case "/delete-fieldProduction":
                List<String> idds = Arrays.asList(request.getParameter("ids"));
                for (String id : idds) {
                    fieldProductionEjb.delete(Long.valueOf(id));
                }
                break;
        }
    }
}
