package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.ActivityEjbI;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ActivityController",
        urlPatterns = {
                "/add-activity", "/edit-activity", "/delete-activity", "/view-activities"
        }
)
public class ActivityActions extends BaseController {

    @EJB
    ActivityEjbI activityEjb;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        try {
            switch (action) {
                case "/add-activity":
                    response.setContentType("application/json");
                    response.getWriter().print(jsonMapper.writeValueAsString(activityEjb.addActivity(request.getParameterMap(), id)));
                    break;
                case "/edit-activity":

                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        try {
            if ("/view-activities".equals(action)) {
                response.setContentType("application/json");
                response.getWriter().print(jsonMapper.writeValueAsString(activityEjb.listActivities(id)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        try {
            if ("/delete-activity".equals(action)) {
                activityEjb.deleteActivities(request.getParameter("activityLabels"),id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
