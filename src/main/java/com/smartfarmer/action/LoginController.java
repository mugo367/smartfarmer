package com.smartfarmer.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.dao.interfaces.LoginBeanDaoI;
import com.smartfarmer.ejb.interfaces.LoginEjbI;
import com.smartfarmer.model.LoginResponse;

import javax.ejb.EJB;
import javax.inject.Inject;
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
    @Inject
    LoginBeanDaoI loginBean;

    @EJB
    LoginEjbI loginEjb;

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
        LoginResponse loginResponse = loginEjb.authenticate(request.getParameterMap(), session);
        session.setAttribute("loginUserData", loginResponse);

        response.setContentType("application/json");
        response.getWriter().print(new ObjectMapper().writeValueAsString(loginResponse));

    }
    /*private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    }*/
}
