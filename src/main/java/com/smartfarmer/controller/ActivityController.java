package com.smartfarmer.controller;

import com.google.gson.Gson;
import com.smartfarmer.dao.DaoI;
import com.smartfarmer.model.Activity;

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
        name = "ActivityController",
        urlPatterns = {
                "/add-activity", "/edit-activity", "/delete-activity", "/view-activities"
        }
)
public class ActivityController extends BaseController {

    @Inject
    @Named("ActivityDao")
    DaoI<Activity> activityDao;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-activity":
                    addActivity(request, response);
                    break;
                case "/edit-activity":
                    editActivity(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if ("/view-activities".equals(action)) {
                viewActivities(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if ("/delete-activity".equals(action)) {
                deleteActivity(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //add activity
    private void addActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
       Activity activity = new Activity(
                request.getParameter("activityLabel"),
                request.getParameter("activityName"),
                request.getParameter("activityDescription"),
               (Integer) request.getSession().getAttribute("uid")
        );
        try {
            activityDao.add(activity);
        } catch (ParseException | SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));

    }
    //delete activity
    private void deleteActivity(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = (Integer) request.getSession().getAttribute("uid");
            String activity = request.getParameter("activityLabels");

            List<String> activityLabels = new Gson().fromJson( activity, List.class );

            for(String activityLabel : activityLabels) {
                activityDao.delete(activityLabel, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
    //edit activity
    private void editActivity(HttpServletRequest request, HttpServletResponse response) {
        Activity activity = new Activity(
                request.getParameter("activityLabel"),
                request.getParameter("activityName"),
                request.getParameter("activityDescription"),
                (Integer) request.getSession().getAttribute("uid")
        );
        try {
            if(activityDao.update(activity)){

            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }

    }
    //view activities
    private void viewActivities(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = (Integer) request.getSession().getAttribute("uid");
        List<Activity> activityList;
        try {
            activityList = activityDao.read(id);
            resultWrapper.setList(activityList);
            response.setContentType("application/json");
            response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }


    }
}
