package com.smartfarmer.controller;

import com.google.gson.Gson;
import com.smartfarmer.dao.DaoI;
import com.smartfarmer.dao.FieldDetailDaoI;
import com.smartfarmer.model.Production;
import com.smartfarmer.model.enumFiles.Unit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(
        name = "ProductionController",

        urlPatterns = {
                "/add-production","/edit-production","/delete-production","view-productions"
        }
)
public class ProductionController extends BaseController {

    @Inject
    @Named("ProductionDao")
    DaoI<Production> productionDao;
    @Inject
    FieldDetailDaoI fieldDetailDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/view-productions".equals(action)) {
            viewProductions(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action){
            case "/add-production":
                addProduction(request, response);
                break;
            case "/edit-production":
                editProduction(request, response);
                break;

        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if ("/delete-production".equals(action)) {
                deleteProduction(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   // to add productions
    private void addProduction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        int id = (Integer) request.getSession().getAttribute("uid");
        try {
            Production production = new Production(
                    request.getParameter("productionLabel"),
                    new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("productionDate")),
                    Integer.parseInt(request.getParameter("fieldName")),
                    Double.parseDouble(request.getParameter("productionQuantity")),
                    Unit.valueOf(request.getParameter("unit")),
                    request.getParameter("productionDetails"),
                    id
            );
            productionDao.add(production);
        } catch (ParseException | SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));
    }

    // to delete productions
    private void deleteProduction(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = (Integer) request.getSession().getAttribute("uid");
            String production = request.getParameter("productionLabels");

            List<String> productionLabels = new Gson().fromJson( production, List.class );

            for(String productionLabel : productionLabels) {
                productionDao.delete(productionLabel, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
    // to edit productions
    private void editProduction(HttpServletRequest request, HttpServletResponse response) {

    }
    // to view productions
    private void viewProductions(HttpServletRequest request, HttpServletResponse response) {
        int id = (Integer) request.getSession().getAttribute("uid");
        List<Production> productionList;
        try {
            productionList = productionDao.read(id);
            jsonMapper.setDateFormat(df);
            resultWrapper.setList(productionList);
            response.setContentType("application/json");
            response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));

        } catch (SQLException | ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
