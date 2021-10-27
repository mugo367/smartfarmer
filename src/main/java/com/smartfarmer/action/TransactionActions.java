package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.TransactionEjbI;

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
public class TransactionActions extends BaseController {

    @EJB
    private TransactionEjbI transactionEjb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");
        jsonMapper.setDateFormat(df);

        if ("/view-transactions".equals(action)) {
            response.setContentType("application/json");
            response.getWriter().print(jsonMapper.writeValueAsString(transactionEjb.listTransactions(id)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");

        switch (action) {
            case "/add-transaction":
                response.setContentType("application/json");
                response.getWriter().print(jsonMapper.writeValueAsString(transactionEjb.addTransaction(request.getParameterMap(), id)));
                break;
            case "/edit-transaction":
                System.out.println("===========================");
                break;
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int id = (Integer) request.getSession().getAttribute("uid");

        if ("/delete-transaction".equals(action)) {
           transactionEjb.deleteTransactions(request.getParameter("transactionLabels"),id);
        }
    }
}
