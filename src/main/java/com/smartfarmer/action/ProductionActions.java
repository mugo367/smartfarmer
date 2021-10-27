package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.ProductionEjbI;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ProductionController",

        urlPatterns = {
                "/add-production","/edit-production","/delete-production","view-productions"
        }
)
public class ProductionActions extends BaseController {

    @EJB
    ProductionEjbI productionEjb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        jsonMapper.setDateFormat(df);
        if ("/view-productions".equals(action)) {
            response.setContentType("application/json");
            response.getWriter().print(jsonMapper.writeValueAsString(productionEjb.listProductions(id)));
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        switch (action){
            case "/add-production":
                response.setContentType("application/json");
                response.getWriter().print(jsonMapper.writeValueAsString(productionEjb.addProduction(request.getParameterMap(), id)));
                break;
            case "/edit-production":

                break;

        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");

        if ("/delete-production".equals(action)) {
            productionEjb.deleteProduction(request.getParameter("productionLabels"), id);
        }
    }
}
