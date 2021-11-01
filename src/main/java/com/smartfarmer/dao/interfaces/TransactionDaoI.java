package com.smartfarmer.dao.interfaces;

import com.smartfarmer.entities.Transaction;

public interface TransactionDaoI extends DaoI<Transaction, Long>{
    Double getTotalIncome(int id) ;
    Double getTotalExpense(int id) ;
}
