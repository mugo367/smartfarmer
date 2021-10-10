package com.smartfarmer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.dao.ActivityDao;
import com.smartfarmer.model.Activity;
import com.smartfarmer.model.ResultWrapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
public class ActivityController extends HttpServlet {
    private ActivityDao activityDao;
    @Override
    public void init(ServletConfig config) throws ServletException {
        activityDao = new ActivityDao();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                case "/view-activities":
                    viewActivities(request, response);
                    break;

            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    //add activity
    private void addActivity(HttpServletRequest request, HttpServletResponse response) {
        Activity activity = new Activity(
                request.getParameter("activityLabel"),
                request.getParameter("activityName"),
                request.getParameter("activityDescription"),
                1
        );
        try {
            if(activityDao.add(activity)) {
                response.sendRedirect("./viewActivities.jsp");
            }else{

                response.sendRedirect("./Activities.jsp");
            }

        } catch (ParseException | SQLException | IOException e) {
            e.printStackTrace();
        }

    }
    //delete activity
    private void deleteActivity(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(activityDao.delete(request.getParameter("activityLabel"), Integer.parseInt(request.getParameter("uid")))){
                response.sendRedirect("./viewEquipments.jsp");
            }

            //check on else
        } catch (ParseException | SQLException | IOException e) {
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
