package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.RegisterEjbI;
import com.smartfarmer.entities.Farmer;
import com.smartfarmer.model.RegisterResponse;
import lombok.SneakyThrows;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        name = "RegisterController",
        urlPatterns = "/register"
)
public class RegisterController extends BaseController {
    @EJB
    RegisterEjbI registerEjb;

    private Farmer farmer = new Farmer();


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        transform(farmer, request.getParameterMap());
        response.setContentType("application/json");
        jsonMapper.setDateFormat(df);

        RegisterResponse registerResponse = registerEjb.register(farmer);

        response.getWriter().print(jsonMapper.writeValueAsString(registerResponse));

        if(registerResponse.isRegisterError()){
            response.sendRedirect("./register.jsp");
            session.setAttribute("message", registerResponse.getRegisterErrorMsg());
        }else{
            response.sendRedirect("./login.jsp");
        }
    }
}
