package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.entities.Transaction;
import com.smartfarmer.util.ModelListWrapper;

public interface TransactionEjbI extends EjbI<Transaction>{

    ModelListWrapper<Transaction> listExpenses(Transaction filter, int start, int limit);
    ModelListWrapper<Transaction> listIncomes(Transaction filter, int start, int limit);

    double totalIncomes(Transaction tr);
    double totalExpenses(Transaction tr);

}
