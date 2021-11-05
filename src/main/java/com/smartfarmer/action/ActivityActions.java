package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.ActivityEjbI;
import com.smartfarmer.entities.Activity;
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
        name = "ActivityController",
        urlPatterns = {
                "/add-activity", "/edit-activity", "/delete-activity", "/view-activities"
        }
)
public class ActivityActions extends BaseController {


    @EJB
    ActivityEjbI activityEjb;

    private Activity activity = new Activity();

    @SneakyThrows
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/add-activity":

                transform(activity, request.getParameterMap());
                activityEjb.add(activity);

                handleResponse(response);
                break;
            case "/edit-activity":

                break;
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/view-activities".equals(action)) {
            transform(activity, request.getParameterMap());
            handleResponse(response, activityEjb.list(activity, 0, 0).getList());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/delete-activity".equals(action)) {
            List<String> ids = Arrays.asList(request.getParameter("ids"));
            for(String activityID : ids) {
                activityEjb.delete(Long.valueOf(activityID));
            }
        }
    }
}
