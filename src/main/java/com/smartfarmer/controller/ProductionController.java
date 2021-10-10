package com.smartfarmer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.dao.ProductionDao;
import com.smartfarmer.model.Production;
import com.smartfarmer.model.ResultWrapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebServlet(
        name = "ProductionController",

        urlPatterns = {
                "/add-production","/edit-production","/delete-production","view-productions"
        }
)
public class ProductionController extends HttpServlet {
    private ProductionDao productionDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productionDao = new ProductionDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action){
            case "/add-production":
                addProduction(request, response);
                break;
            case "/delete-production":
                deleteProduction(request, response);
                break;
            case "/edit-production":
                editProduction(request, response);
                break;
            case "/view-productions":
                viewProductions(request, response);
                break;

        }
    }
   // to add productions
    private void addProduction(HttpServletRequest request, HttpServletResponse response) {

    }

    // to delete productions
    private void deleteProduction(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(productionDao.delete(request.getParameter("productionLabel"), Integer.parseInt(request.getParameter("uid")))){

            }

            //check on else
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
    // to edit productions
    private void editProduction(HttpServletRequest request, HttpServletResponse response) {

    }
    // to view productions
    private void viewProductions(HttpServletRequest request, HttpServletResponse response) {
//int id = Integer.parseInt(request.getParameter("uid"));
        List<Production> productionList;
        try {
            productionList = productionDao.read(1);
            ObjectMapper mapper = new ObjectMapper();
            ResultWrapper wrapper = new ResultWrapper();
            wrapper.setList(productionList);
            response.setContentType("application/json");
            response.getWriter().print(mapper.writeValueAsString(wrapper));

        } catch (SQLException | ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
