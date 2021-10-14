package com.smartfarmer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.dao.FieldDetailDaoI;
import com.smartfarmer.model.Farmer;
import com.smartfarmer.model.Field;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.model.enumFiles.FieldStatus;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebServlet(
        name = "FieldDetailsController",
        urlPatterns = {
                "/add-field", "/delete-field", "/edit-fieldDetails", "/view-fields"
        }
)
public class FieldDetailsController extends BaseController {
    @Inject
    FieldDetailDaoI fieldDetailDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try{
            switch (action){
                case("/add-field"):
                    addField(request, response);
                    break;
                case ("/delete-field"):
                    deleteField(request, response);
                    break;
                case("/editFieldDetails"):
                    editFieldDetails(request, response);
                    break;
            }
        }catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try{
            if ("/view_fields".equals(action)) {
                viewFields(request, response);
            }
        }catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    // to add field details
    private void addField(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = (Integer) request.getSession().getAttribute("uid");
        try {
            Farmer farmerDetails = (Farmer) request.getSession().getAttribute("details");
            Double totalFieldSize = farmerDetails.getFarmSize();
            Double usedField = fieldDetailDao.getUsedFieldSize(id);
            Double remainingFieldSize = totalFieldSize - usedField;


            if(remainingFieldSize !=0 & Double.parseDouble(request.getParameter("fieldSize"))>=remainingFieldSize){
                Field field = new Field(
                        request.getParameter("fieldLabel"),
                        request.getParameter("fieldName"),
                        Double.parseDouble(request.getParameter("fieldSize")),
                        FieldStatus.valueOf(request.getParameter("fieldStatus")),
                        id
                );
                try {
                    fieldDetailDao.add(field);
                } catch (ParseException | SQLException e) {
                    resultWrapper.setSuccess(false);
                    resultWrapper.setMessage(e.getMessage());
                }
            }

        } catch (SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));

    }
    //to delete a field
    private void deleteField(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(fieldDetailDao.delete(request.getParameter("fieldLabel"), Integer.parseInt(request.getParameter("uid")))){
                response.sendRedirect("./viewFields.jsp");
            }

            //check on else
        } catch (ParseException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    // to edit field details
    private void editFieldDetails(HttpServletRequest request, HttpServletResponse response) {
        Field field = new Field(
                request.getParameter("fieldLabel"),
                request.getParameter("fieldName"),
                Double.parseDouble(request.getParameter("fieldSize")),
                FieldStatus.valueOf(request.getParameter("fieldStatus")),
                Integer.parseInt(request.getParameter("uid"))
        );
        try {
            if(fieldDetailDao.update(field)){

            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    // to view fields
    private void viewFields(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //int id = Integer.parseInt(request.getParameter("uid"));
        List<Field> fieldList;
        try {

            fieldList = fieldDetailDao.read(1);
            ObjectMapper mapper = new ObjectMapper();
            ResultWrapper wrapper = new ResultWrapper();
            wrapper.setList(fieldList);
            response.setContentType("application/json");
            response.getWriter().print(mapper.writeValueAsString(wrapper));

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
