package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.LoginBeanDaoI;
import com.smartfarmer.ejb.interfaces.LoginEjbI;
import com.smartfarmer.entities.Farmer;
import com.smartfarmer.model.LoginBean;
import com.smartfarmer.model.LoginResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Stateless
public class LoginEjb implements LoginEjbI {
    @Inject
    LoginBeanDaoI loginBeanDao;
    public  LoginResponse authenticate(LoginBean login, HttpSession session) {

        LoginResponse loginResponse = new LoginResponse();

        try{
            Farmer farmer = loginBeanDao.getUser(login);

            if(farmer !=null){

                loginResponse.setSessionId((int) farmer.getId());
                loginResponse.setUsername(login.getUsername());
                loginResponse.setDetails(farmer);
                loginResponse.setRedirectPage("./indexMain.jsp");

                session.setAttribute("username",login.getUsername());
                session.setAttribute("uid", farmer.getId());
                session.setAttribute("details", farmer);

            }else {
                loginResponse.setLoginError(true);
                loginResponse.setLoginErrorMsg("Invalid Login Details");
            }

        }catch (Exception ex){
            ex.printStackTrace();
            loginResponse.setLoginError(true);
            loginResponse.setLoginErrorMsg("Invalid Login Details, '" + ex.getMessage());
        }

        return loginResponse;
    }
}
