package com.smartfarmer.action;

import com.smartfarmer.ejb.interfaces.TransactionEjbI;
import com.smartfarmer.entities.Transaction;
import lombok.SneakyThrows;

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

    private Transaction transaction = new Transaction();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/view-transactions".equals(action)) {
            transform(transaction, request.getParameterMap());
            handleResponse(response, transactionEjb.list(transaction, 0, 0).getList());
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/add-transaction":
                transform(transaction, request.getParameterMap());
                transactionEjb.add(transaction);

                handleResponse(response);
                break;
            case "/edit-transaction":
                System.out.println("===========================");
                break;
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/delete-transaction".equals(action)) {

        }
    }
}
