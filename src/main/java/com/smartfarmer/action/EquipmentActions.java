package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.EquipmentEjbI;
import com.smartfarmer.entities.Equipment;
import lombok.SneakyThrows;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name="EquipmentController",
        urlPatterns = {
                "/add-equipment", "/delete-equipment", "/edit-equipment", "/view-equipments"
        }
)

public class EquipmentActions extends BaseController {

    @EJB
    EquipmentEjbI equipmentEjb;

    private Equipment equipment = new Equipment();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/view-equipments".equals(action)) {
            transform(equipment, request.getParameterMap());
            handleResponse(response, equipmentEjb.listEquipments(equipment, 0, 0).getList());
        }

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");

        switch (action) {
            case "/add-equipment":
                transform(equipment, request.getParameterMap());
                equipmentEjb.addEquipment(equipment);

                handleResponse(response);break;
            case "/edit-equipment":

                break;

        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/delete-equipment".equals(action)) {

        }
    }

}
