package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.model.ResultWrapper;

import java.util.Map;

public interface TransactionEjbI {
    ResultWrapper addTransaction(Map<String, String[]> formData, int id);
    ResultWrapper listTransactions(int id);
    void deleteTransactions(String transactions, int id);
}
