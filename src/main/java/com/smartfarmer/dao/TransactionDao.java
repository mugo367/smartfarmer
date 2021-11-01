package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.TransactionDaoI;
import com.smartfarmer.entities.Transaction;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class TransactionDao extends DaoImplementation<Transaction, Long> implements TransactionDaoI {

    @Inject
    private javax.persistence.EntityManager entityManager;

    public TransactionDao() {
        super(Transaction.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public Double getTotalIncome(int id) {

        String query ="SELECT SUM(transactionCost) FROM transactions where uid= ? AND transactionType ='Income'";

        Double totalIncome = null;

        return totalIncome;

    }

    public Double getTotalExpense(int id) {

        String query ="SELECT SUM(transactionCost) FROM transactions where uid=? AND transactionType ='Expense'";

        Double totalExpense = null;

        return totalExpense;

    }
}
