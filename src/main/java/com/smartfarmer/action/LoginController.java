package com.smartfarmer.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.ejb.interfaces.LoginEjbI;
import com.smartfarmer.model.LoginBean;
import com.smartfarmer.model.LoginResponse;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "LoginController",
        urlPatterns = "/login"
)
public class LoginController extends BaseController {

    @EJB
    LoginEjbI loginEjb;

    private LoginBean loginBean = new LoginBean();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<LoginResponse> loginResponse = new ArrayList<LoginResponse>();


        if (session.getAttribute("loginUserData") != null)
            loginResponse.add((LoginResponse) session.getAttribute("loginUserData"));

        resultWrapper.setList(loginResponse);

        resp.setContentType("application/json");
        resp.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        transform(loginBean, request.getParameterMap());

        LoginResponse loginResponse = loginEjb.authenticate(loginBean, session);
        session.setAttribute("loginUserData", loginResponse);

        response.setContentType("application/json");
        response.getWriter().print(new ObjectMapper().writeValueAsString(loginResponse));

    }
}
