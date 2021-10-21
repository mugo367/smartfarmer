package com.smartfarmer.controller;

import com.google.gson.Gson;
import com.smartfarmer.dao.DaoI;
import com.smartfarmer.model.Transaction;
import com.smartfarmer.model.enumFiles.TransactionType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(
        name ="TransactionController",
        urlPatterns = {
                "/add-transaction","/edit-transaction","/delete-transaction","/view-transactions"
        }
)
public class TransactionController extends BaseController {

    @Inject
    @Named("TransactionDao")
    DaoI<Transaction> transactionDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if ("/view-transactions".equals(action)) {
                viewTransactions(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-transaction":
                    addTransaction(request, response);
                    break;
                case "/edit-transaction":
                    editTransaction(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if ("/delete-transaction".equals(action)) {
                deleteTransaction(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // to add transactions
    private void addTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
       try{
           Transaction transaction = new Transaction(
                   new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("transactionDate")),
                   TransactionType.valueOf(request.getParameter("transactionType")),
                   request.getParameter("transactionLabel"),
                   Double.parseDouble(request.getParameter("costPerUnit")),
                   Integer.parseInt(request.getParameter("units")),
                   Double.parseDouble(request.getParameter("costPerUnit"))*Integer.parseInt(request.getParameter("units")),
                   request.getParameter("transactionDetails"),
                   (Integer) request.getSession().getAttribute("uid")

           );
           transactionDao.add(transaction);
       } catch (ParseException | SQLException e) {
           resultWrapper.setSuccess(false);
           e.printStackTrace();
           resultWrapper.setMessage(e.getMessage());
       }
        response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));

    }
    // to delete transactions
    private void deleteTransaction(HttpServletRequest request, HttpServletResponse response) {

        try {
            int id = (Integer) request.getSession().getAttribute("uid");
            String transactions = request.getParameter("transactionLabels");

            List<String> transactionLabels = new Gson().fromJson( transactions, List.class );

            for(String transactionLabel : transactionLabels) {
                transactionDao.delete(transactionLabel, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
    //to edit transactions
    private void editTransaction(HttpServletRequest request, HttpServletResponse response) {

    }
    //to view transactions
    private void viewTransactions(HttpServletRequest request, HttpServletResponse response) {
        int id = (Integer) request.getSession().getAttribute("uid");
        List<Transaction> transactionList;

        try {
            transactionList = transactionDao.read(id);
            jsonMapper.setDateFormat(df);
            resultWrapper.setList(transactionList);
            response.setContentType("application/json");
            response.getWriter().print(jsonMapper.writeValueAsString(resultWrapper));

        } catch (SQLException | ParseException | IOException e) {
            e.printStackTrace();
        }
    }


}
