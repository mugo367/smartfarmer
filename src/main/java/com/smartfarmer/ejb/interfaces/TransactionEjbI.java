package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.entities.Transaction;

import java.util.Optional;

public interface TransactionEjbI {
    Transaction addTransaction(Transaction transaction) throws Exception;
    Transaction editTransaction(Transaction transaction);
    ModelListWrapper<Transaction> listActivities(Transaction filter, int start, int limit);
    void deleteTransaction(Long id);
    Optional<Transaction> findById(Long id);
}
