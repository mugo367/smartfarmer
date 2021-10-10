package com.smartfarmer.controller;

import com.smartfarmer.bean.LoginBean;
import com.smartfarmer.bean.LoginBeanI;
import com.smartfarmer.dao.LoginBeanDao;
import org.apache.commons.beanutils.BeanUtils;

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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        authenticate(request, response);

    }
    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        LoginBeanI loginBean = new LoginBeanDao();

        try{

            LoginBean login = new LoginBean( );
            BeanUtils.populate(login, request.getParameterMap());

            if(loginBean.checkUser(login)){
                session.setAttribute("username",login.getUsername());
                session.setAttribute("uid", loginBean.getFarmerDetails(login).getId());
                session.setAttribute("details", loginBean.getFarmerDetails(login));


                response.sendRedirect("./home.jsp");
            }
            else{
                session.setAttribute("LOGIN_MSG", "Invalid Login Details");
                response.sendRedirect("./login.jsp");
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            response.sendRedirect("./login.jsp");
        }
    }
}
