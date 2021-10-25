package com.smartfarmer.action;

import com.smartfarmer.ejb.TransactionEjb;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name ="TransactionController",
        urlPatterns = {
                "/add-transaction","/edit-transaction","/delete-transaction","/view-transactions"
        }
)
public class TransactionController extends BaseController {

    @EJB
    private TransactionEjb transactionEjb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        jsonMapper.setDateFormat(df);

        try {
            if ("/view-transactions".equals(action)) {
                response.setContentType("application/json");
                response.getWriter().print(jsonMapper.writeValueAsString(transactionEjb.listTransactions(id)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        try {
            switch (action) {
                case "/add-transaction":
                    response.setContentType("application/json");
                    response.getWriter().print(jsonMapper.writeValueAsString(transactionEjb.addTransaction(request.getParameterMap(), id)));
                    break;
                case "/edit-transaction":
                    System.out.println("===========================");
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");

        try {
            if ("/delete-transaction".equals(action)) {
               transactionEjb.deleteTransactions(request.getParameter("transactionLabels"),id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
