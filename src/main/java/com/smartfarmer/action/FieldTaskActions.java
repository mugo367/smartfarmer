package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.FieldTaskEjbI;
import com.smartfarmer.entities.FieldTask;
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
        name = "FieldTaskController",
        urlPatterns = {
                "/add-task", "/delete-task", "/edit-task", "/view-tasks"
        }
)

public class FieldTaskActions extends BaseController{
    @EJB
    FieldTaskEjbI fieldTaskEjb;

    private FieldTask fieldTask = new FieldTask();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case ("/add-task"):
                transform(fieldTask, request.getParameterMap());
                fieldTaskEjb.add(fieldTask);

                handleResponse(response);
                break;
            case ("/edit-task"):

                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/view-tasks".equals(action)) {
            transform(fieldTask, request.getParameterMap());
            handleResponse(response, fieldTaskEjb.list(fieldTask, 0, 0).getList());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/delete-task".equals(action)) {
            List<String> ids = Arrays.asList(request.getParameter("ids"));
            for(String id : ids) {
                fieldTaskEjb.delete(Long.valueOf(id));
            }
        }
    }
}
