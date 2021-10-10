package com.smartfarmer.dao;

import com.smartfarmer.model.Farmer;
import com.smartfarmer.model.Transaction;
import com.smartfarmer.model.enumFiles.TransactionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao implements DaoI<Transaction> {
    Controller controller = new Controller();
    @Override
    public boolean add(Transaction transaction) throws ParseException, SQLException {
        String query = "INSERT INTO transactions (transactionDate, transactionType, transactionLabel, costPerUnit, units, transactionCost, transactionDetails, uid) VALUES (" +
                "'"+new java.sql.Date(transaction.getTransactionDate().getTime())+"', '"+transaction.getTransactionType()+"', '"+transaction.getTransactionLabel()+"', " +
                "'"+transaction.getCostPerUnit()+"', '"+transaction.getUnits()+"', '"+transaction.getTransactionCost()+"' '"+transaction.getTransactionDetails()+"',"+transaction.getUid()+"";
        return controller.writeData(query)==1;
    }

    @Override
    public List<Transaction> read(int id) throws ParseException, SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        String query= "SELECT * FROM transactions WHERE uid = "+id+"";
        ResultSet resultSet = controller.readData(query);
        while (resultSet.next()){
            Transaction transaction = new Transaction();
            transaction.setTransactionDate(new java.util.Date(resultSet.getDate(2).getTime()));
            transaction.setTransactionType(TransactionType.valueOf(resultSet.getString(3)));
            transaction.setTransactionLabel(resultSet.getString(4));
            transaction.setCostPerUnit(resultSet.getDouble(5));
            transaction.setUnits(resultSet.getInt(6));
            transaction.setTransactionCost(resultSet.getDouble(7));
            transaction.setTransactionDetails(resultSet.getString(8));
        }
        return transactionList;
    }

    @Override
    public boolean update(Transaction transaction) throws ParseException, SQLException {
        String query = "UPDATE transactions SET transactionType = '"+transaction.getTransactionLabel()+"', costPerUnit ="+transaction.getCostPerUnit()+", units ="+transaction.getUnits()+", " +
                "transactionCost = "+transaction.getTransactionCost()+", transactionDetails ='"+transaction.getTransactionDetails()+"'";
        return controller.writeData(query)==1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        String query = "DELETE FROM transactions WHERE transactionLabel = '"+label+"', uid = "+id+"";
        return controller.writeData(query)==1;
    }

    public Double getTotalIncome(Farmer farmer) throws SQLException {
        String query ="SELECT SUM(transactionCost) FROM transactions where uid="+farmer.getId()+" AND transactionType ='Income'";
        ResultSet resultSet = controller.readData(query);
        Double totalIncome = null;
        while (resultSet.next()){
            String income= resultSet.getString(1);
            if(income!=null) totalIncome = new Double(income);
        }
        return totalIncome;

    }

    public Double getTotalExpense(Farmer farmer) throws SQLException {
        String query ="SELECT SUM(transactionCost) FROM transactions where uid="+farmer.getId()+" AND transactionType ='Expense'";
        ResultSet resultSet = controller.readData(query);
        Double totalExpense = null;
        while (resultSet.next()){
            String expense= resultSet.getString(1);
            if(expense!=null) totalExpense = new Double(expense);
        }
        return totalExpense;

    }
}
