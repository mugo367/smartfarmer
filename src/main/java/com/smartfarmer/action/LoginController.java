package com.smartfarmer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.bean.LoginBean;
import com.smartfarmer.dao.LoginBeanDaoI;
import com.smartfarmer.model.LoginResponse;
import org.apache.commons.beanutils.BeanUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "LoginController",
        urlPatterns = "/login"
)
public class LoginController extends HttpServlet {
    @Inject
    LoginBeanDaoI loginBean;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        authenticate(request, response);

    }
    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        LoginResponse loginResponse = new LoginResponse();

        try{

            LoginBean login = new LoginBean( );
            BeanUtils.populate(login, request.getParameterMap());

            if(loginBean.checkUser(login)){

                loginResponse.setSessionId(loginBean.getFarmerDetails(login).getId());
                loginResponse.setUsername(login.getUsername());
                loginResponse.setDetails(loginBean.getFarmerDetails(login));
                loginResponse.setRedirectPage("./indexMain.jsp");

                session.setAttribute("username",login.getUsername());
                session.setAttribute("uid", loginBean.getFarmerDetails(login).getId());
                session.setAttribute("details", loginBean.getFarmerDetails(login));

            }else {
                loginResponse.setLoginError(true);
                loginResponse.setLoginErrorMsg("Invalid Login Details");
            }

        }catch (Exception ex){
            ex.printStackTrace();
            loginResponse.setLoginError(true);
            loginResponse.setLoginErrorMsg("Invalid Login Details, '" + ex.getMessage());
        }

        response.setContentType("application/json");
        response.getWriter().print(new ObjectMapper().writeValueAsString(loginResponse));

    }
}
