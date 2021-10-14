package com.smartfarmer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartfarmer.dao.DaoI;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.model.Transaction;
import com.smartfarmer.model.enumFiles.TransactionType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
                "/add-transaction","/edit-transaction","/delete-transaction","view-transactions"
        }
)
public class TransactionController extends HttpServlet {

    @Inject
    @Named("TransactionDao")
    DaoI<Transaction> transactionDao;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-transaction":
                    addTransaction(request, response);
                    break;
                case "/delete-transaction":
                    deleteTransaction(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            if ("/view-transactions".equals(action)) {
                viewTransactions(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    // to add transactions
    private void addTransaction(HttpServletRequest request, HttpServletResponse response) {
       try{
           Transaction transaction = new Transaction(
                   new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("transactionDate")),
                   TransactionType.valueOf(request.getParameter("transactionType")),
                   request.getParameter("transactionLabel"),
                   Double.parseDouble(request.getParameter("costPerUnit")),
                   Integer.parseInt(request.getParameter("units")),
                   Double.parseDouble(request.getParameter("costPerUnit"))*Integer.parseInt(request.getParameter("units")),
                   request.getParameter("transactionDetails"),
                   Integer.parseInt(request.getParameter("uid"))
           );
           if(transactionDao.add(transaction)){
               System.out.println("Employee was added Successfully");

           }else{
               System.out.println("An error occurred during the process !!");

           }
       } catch (ParseException | SQLException e) {
           e.printStackTrace();

       }


    }
    // to delete transactions
    private void deleteTransaction(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(transactionDao.delete(request.getParameter("transactionLabel"), Integer.parseInt(request.getParameter("uid")))){

            }

            //check on else
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
    //to edit transactions
    private void editTransaction(HttpServletRequest request, HttpServletResponse response) {

    }
    //to view transactions
    private void viewTransactions(HttpServletRequest request, HttpServletResponse response) {
//int id = Integer.parseInt(request.getParameter("uid"));
        List<Transaction> transactionList;
        try {
            transactionList = transactionDao.read(1);
            ObjectMapper mapper = new ObjectMapper();
            ResultWrapper wrapper = new ResultWrapper();
            wrapper.setList(transactionList);
            response.setContentType("application/json");
            response.getWriter().print(mapper.writeValueAsString(wrapper));

        } catch (SQLException | ParseException | IOException e) {
            e.printStackTrace();
        }
    }


}
