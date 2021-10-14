package com.smartfarmer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.dao.DaoI;
import com.smartfarmer.model.Activity;
import com.smartfarmer.model.ResultWrapper;

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
                case "/delete-activity":
                    deleteActivity(request, response);
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
            throw new ServletException(ex);
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
            activityDao.delete(request.getParameter("activityLabel"), Integer.parseInt(request.getParameter("uid")));

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
                Integer.parseInt(request.getParameter("uid"))
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
        //int id = Integer.parseInt(request.getParameter("uid"));
        List<Activity> activityList;
        try {

            activityList = activityDao.read(1);
            ObjectMapper mapper = new ObjectMapper();
            ResultWrapper wrapper = new ResultWrapper();
            wrapper.setList(activityList);
            response.setContentType("application/json");
            response.getWriter().print(mapper.writeValueAsString(wrapper));

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }


    }
}
