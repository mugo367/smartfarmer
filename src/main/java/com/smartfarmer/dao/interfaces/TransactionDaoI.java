package com.smartfarmer.dao.interfaces;

import com.smartfarmer.entities.Transaction;
import com.smartfarmer.util.ModelListWrapper;

public interface TransactionDaoI extends DaoI<Transaction, Long>{
    double getTotalIncome(Transaction tr) ;
    double getTotalExpense(Transaction tr) ;
    ModelListWrapper<Transaction> listExpenses(Transaction filter, int start, int limit );
    ModelListWrapper<Transaction> listIncomes(Transaction filter, int start, int limit );
}
