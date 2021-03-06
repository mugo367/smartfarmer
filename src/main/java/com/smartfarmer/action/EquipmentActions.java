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
import java.util.Arrays;
import java.util.List;

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
            handleResponse(response, equipmentEjb.list(equipment, 0, 0).getList());
        }

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/add-equipment".equals(action)) {
            transform(equipment, request.getParameterMap());
            equipmentEjb.add(equipment);

            handleResponse(response);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/delete-equipment".equals(action)) {
            List<String> ids = Arrays.asList(request.getParameter("ids"));
            for(String id : ids) {
                equipmentEjb.delete(Long.valueOf(id));
            }
        }
    }

}
