package com.smartfarmer.ejb;

import com.google.gson.Gson;
import com.smartfarmer.dao.interfaces.TransactionDaoI;
import com.smartfarmer.ejb.interfaces.TransactionEjbI;
import com.smartfarmer.entities.Transaction;
import com.smartfarmer.entities.enumFiles.TransactionType;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.util.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class TransactionEjb implements TransactionEjbI {

    @Inject
    TransactionDaoI<Transaction> transactionDao;

    public ResultWrapper addTransaction(Map<String, String[]> formData, int id){
        ResultWrapper resultWrapper = new ResultWrapper();

        Transaction transaction = new Transaction();

        try{
            BeanUtils.populate(transaction, formData);

            if (transaction.getTransactionType() == null && transaction.getTransactionTypeStr() != null)
                transaction.setTransactionType(TransactionType.valueOf(transaction.getTransactionTypeStr()));

            if (transaction.getCostPerUnit() != 0 && transaction.getUnits() != 0)
                transaction.setTransactionCost(transaction.getCostPerUnit()*transaction.getUnits());

            transaction.setUid(id);

        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            resultWrapper.setMessage(e.getMessage());
            resultWrapper.setSuccess(false);
            return resultWrapper;
        }

        try {
            transactionDao.add(transaction);
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }

        return resultWrapper;
    }

    public ResultWrapper listTransactions(int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        List<Transaction> transactionList = new ArrayList<>();

        try {
            transactionList = transactionDao.read(id);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }

        resultWrapper.setList(transactionList);
        return resultWrapper;
    }

    public void deleteTransactions(String transactions, int id) {
        try {
            List<String> transactionLabels = new Gson().fromJson( transactions, List.class );

            for(String transactionLabel : transactionLabels) {
                transactionDao.delete(transactionLabel, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
