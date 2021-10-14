package com.smartfarmer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.dao.DaoI;
import com.smartfarmer.model.Equipment;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.model.enumFiles.Condition;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebServlet(
        name="EquipmentController",
        urlPatterns = {
                "/add-equipment", "/delete-equipment", "/edit-equipment", "list-equipment", "view-equipments"
        }
)

public class EquipmentController extends BaseController {

    @Inject
    @Named("EquipmentDao")
    DaoI<Equipment> equipmentDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-equipment":
                    addEquipment(request, response);
                    break;
                case "/delete-equipment":
                    deleteEquipment(request, response);
                    break;
                case "/edit-equipment":
                    editEquipment(request, response);
                    break;

            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if ("/view-equipments".equals(action)) {
                viewEquipments(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    //to add equipments
    private void addEquipment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Equipment equipment = new Equipment(
                request.getParameter("equipmentLabel"),
                request.getParameter("equipmentName"),
                Condition.valueOf( request.getParameter("equipmentCondition")),
                request.getParameter("equipmentQuantity"),
                (Integer) request.getSession().getAttribute("uid")
        );
        try {
            equipmentDao.add(equipment);
        } catch (ParseException | SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));
    }
    //to delete equipment
    private void deleteEquipment(HttpServletRequest request, HttpServletResponse response){
        try {
            equipmentDao.delete(request.getParameter("equipmentLabel"), Integer.parseInt(request.getParameter("uid")));

            //check on else

        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
    //to edit equipment
    private void editEquipment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Equipment equipment = new Equipment(
                request.getParameter("equipmentLabel"),
                request.getParameter("equipmentName"),
                Condition.valueOf( request.getParameter("equipmentCondition")),
                request.getParameter("equipmentQuantity"),
                (Integer) request.getSession().getAttribute("uid")
        );
        try {
            equipmentDao.update(equipment);
        } catch (ParseException | SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));


    }
    // to view equipments
    private void viewEquipments(HttpServletRequest request, HttpServletResponse response) {
        //int id = Integer.parseInt(request.getParameter("uid"));
            List<Equipment> equipmentList;
        try {

            equipmentList = equipmentDao.read(1);
            ObjectMapper mapper = new ObjectMapper();
            ResultWrapper wrapper = new ResultWrapper();
            wrapper.setList(equipmentList);
            response.setContentType("application/json");
            response.getWriter().print(mapper.writeValueAsString(wrapper));

        } catch (SQLException | ParseException | IOException e) {
            e.printStackTrace();
        }


    }
}
