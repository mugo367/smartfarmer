package com.smartfarmer.action;

import com.smartfarmer.model.LoginResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
    name="Logout",
    urlPatterns = "/logout"
)
public class LogoutAction extends BaseController {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{

        HttpSession session = req.getSession();
        session.invalidate();

        List<LoginResponse> loginResponse = new ArrayList<LoginResponse>();
        loginResponse.add(new LoginResponse());

        resultWrapper.setList(loginResponse);

        res.setContentType("application/json");
        res.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));

    }

}
