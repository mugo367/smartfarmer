package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.EquipmentEjbI;

import javax.inject.Inject;
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

public class EquipmentController extends BaseController {

    @Inject
    EquipmentEjbI equipmentEjb;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        jsonMapper.setDateFormat(df);

        if ("/view-equipments".equals(action)) {
            response.setContentType("application/json");
            response.getWriter().print(jsonMapper.writeValueAsString(equipmentEjb.listEquipments(id)));
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");

        switch (action) {
            case "/add-equipment":
                response.setContentType("application/json");
                response.getWriter().print(jsonMapper.writeValueAsString(equipmentEjb.addEquipment(request.getParameterMap(), id)));
                break;
            case "/edit-equipment":

                break;

        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        if ("/delete-equipment".equals(action)) {
            equipmentEjb.deleteEquipments(request.getParameter("equipmentLabels"), id);
        }
    }

}
