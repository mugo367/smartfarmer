package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.TransactionDaoI;
import com.smartfarmer.model.Transaction;
import com.smartfarmer.model.enumFiles.TransactionType;
import com.smartfarmer.util.EntityManager;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class TransactionDao implements TransactionDaoI<Transaction> {
   @Inject
   EntityManager entityManager;

    @Override
    public boolean add(Transaction transaction) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();
        String query = "INSERT INTO transactions (transactionDate, transactionType, transactionLabel, costPerUnit, units, transactionCost, transactionDetails, uid) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setDate(1, new java.sql.Date(transaction.getTransactionDate().getTime()));
        preparedStatement.setString(2, transaction.getTransactionType().name());
        preparedStatement.setString(3, transaction.getTransactionLabel() );
        preparedStatement.setDouble(4, transaction.getCostPerUnit());
        preparedStatement.setDouble(5, transaction.getUnits() );
        preparedStatement.setDouble(6, transaction.getTransactionCost() );
        preparedStatement.setString(7, transaction.getTransactionDetails() );
        preparedStatement.setInt(8, transaction.getUid());


        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public List<Transaction> read(int id) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();
        List<Transaction> transactionList = new ArrayList<>();
        String query= "SELECT * FROM transactions WHERE uid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Transaction transaction = new Transaction();
            transaction.setId(resultSet.getInt(1));
            transaction.setTransactionDate(new java.util.Date(resultSet.getDate(2).getTime()));
            transaction.setTransactionType(TransactionType.valueOf(resultSet.getString(3)));
            transaction.setTransactionLabel(resultSet.getString(4));
            transaction.setCostPerUnit(resultSet.getDouble(5));
            transaction.setUnits(resultSet.getInt(6));
            transaction.setTransactionCost(resultSet.getDouble(7));
            transaction.setTransactionDetails(resultSet.getString(8));
            transaction.setUid(resultSet.getInt(9));
            transactionList.add(transaction);
        }
        return transactionList;
    }

    @Override
    public boolean update(Transaction transaction) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();
        String query = "UPDATE transactions SET transactionType = ?, costPerUnit =?, units =?, " +
                " transactionCost = ?, transactionDetails = ? WHERE  transactionLabel = ? AND uid =?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, transaction.getTransactionLabel());
        preparedStatement.setDouble(2, transaction.getCostPerUnit());
        preparedStatement.setDouble(3, transaction.getUnits());
        preparedStatement.setDouble(4, transaction.getTransactionCost());
        preparedStatement.setString(5, transaction.getTransactionDetails());
        preparedStatement.setString(6, transaction.getTransactionLabel());
        preparedStatement.setInt(7, transaction.getUid());
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();
        String query = "DELETE FROM transactions WHERE transactionLabel = ? AND uid =?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, label);
        preparedStatement.setInt(2, id);

        return  preparedStatement.executeUpdate() == 1;
    }

    public Double getTotalIncome(int id) throws SQLException {
        Connection conn = entityManager.getConnection();
        String query ="SELECT SUM(transactionCost) FROM transactions where uid= ? AND transactionType ='Income'";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Double totalIncome = null;
        while (resultSet.next()){
            String income= resultSet.getString(1);
            if(income!=null) totalIncome = new Double(income);
        }
        return totalIncome;

    }

    public Double getTotalExpense(int id) throws SQLException {
        Connection conn = entityManager.getConnection();
        String query ="SELECT SUM(transactionCost) FROM transactions where uid=? AND transactionType ='Expense'";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Double totalExpense = null;
        while (resultSet.next()){
            String expense= resultSet.getString(1);
            if(expense!=null) totalExpense = new Double(expense);
        }
        return totalExpense;

    }
}
