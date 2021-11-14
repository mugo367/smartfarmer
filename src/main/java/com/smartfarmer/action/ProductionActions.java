package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.ProductionEjbI;
import com.smartfarmer.entities.Production;
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
        name = "ProductionController",

        urlPatterns = {
                "/add-production","/edit-production","/delete-production","view-productions"
        }
)
public class ProductionActions extends BaseController {

    @EJB
    ProductionEjbI productionEjb;

    private Production production = new Production();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/view-productions".equals(action)) {
            transform(production, request.getParameterMap());
            handleResponse(response, productionEjb.list(production, 0, 0).getList());

        }
    }
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/add-production".equals(action)) {
            transform(production, request.getParameterMap());
            response.getWriter().print(jsonMapper.writeValueAsString(productionEjb.add(production)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/delete-production".equals(action)) {
            List<String> ids = Arrays.asList(request.getParameter("ids"));
            for(String id : ids) {
                productionEjb.delete(Long.valueOf(id));
            }
        }
    }
}
