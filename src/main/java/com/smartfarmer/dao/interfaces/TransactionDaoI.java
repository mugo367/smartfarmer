package com.smartfarmer.dao.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface TransactionDaoI<Transaction> {
    boolean add(Transaction transaction) throws ParseException, SQLException;
    List<Transaction> read(int id) throws SQLException, ParseException;
    boolean update(Transaction transaction) throws ParseException, SQLException;
    boolean delete(String label, int id) throws ParseException, SQLException;
    Double getTotalIncome(int id) throws SQLException;
    Double getTotalExpense(int id) throws SQLException;
}
