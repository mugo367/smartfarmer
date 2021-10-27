package com.smartfarmer.ejb;

import com.smartfarmer.bean.LoginBean;
import com.smartfarmer.dao.interfaces.LoginBeanDaoI;
import com.smartfarmer.ejb.interfaces.LoginEjbI;
import com.smartfarmer.model.LoginResponse;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Stateless
public class LoginEjb implements LoginEjbI {
    @Inject
    LoginBeanDaoI loginBean;
    public  LoginResponse authenticate(Map<String, String[]> params, HttpSession session) {

        LoginResponse loginResponse = new LoginResponse();

        try{

            LoginBean login = new LoginBean( );
            BeanUtils.populate(login, params);

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

        return loginResponse;
    }
}
