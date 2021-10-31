package com.smartfarmer.action;

import com.smartfarmer.dao.FarmerDao;
import com.smartfarmer.entities.Farmer;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(
        name = "RegisterController",
        urlPatterns = "/register"
)
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FarmerDao farmerDao = new FarmerDao();
        PrintWriter output = resp.getWriter();
        try {

            Farmer farmer = new Farmer();
            BeanUtils.populate(farmer, req.getParameterMap());
            if(farmerDao.add(farmer)){
                output.print("Registration was Successfully");
                resp.sendRedirect("./index.jsp");
            }else{
                output.print("An error occurred during the process !!");
                resp.sendRedirect("./register.jsp");
            }
        } catch (IllegalAccessException | SQLException | ParseException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
