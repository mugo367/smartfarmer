package com.smartfarmer.ejb;

import com.smartfarmer.model.Response;
import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.dao.interfaces.TransactionDaoI;
import com.smartfarmer.ejb.interfaces.TransactionEjbI;
import com.smartfarmer.entities.Transaction;
import com.smartfarmer.entities.enumFiles.TransactionType;
import com.smartfarmer.util.AppException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class TransactionEjb implements TransactionEjbI {

    @Inject
    TransactionDaoI transactionDao;

    @Override
    public Response add(Transaction transaction) throws Exception {
        if (transaction == null)
            throw new AppException("Invalid transaction details!!");

        if (transaction.getTransactionType() == null && transaction.getTransactionTypeStr() != null)
            transaction.setTransactionType(TransactionType.valueOf(transaction.getTransactionTypeStr()));

        if (transaction.getCostPerUnit() != 0 && transaction.getUnits() != 0)
            transaction.setTransactionCost(transaction.getCostPerUnit()*transaction.getUnits());

        return new Response(true, "Successfully added",  transactionDao.save(transaction));
    }

    @Override
    public Transaction edit(Transaction transaction) {
        return transactionDao.edit(transaction);
    }

    @Override
    public ModelListWrapper<Transaction> list(Transaction filter, int start, int limit) {
        return transactionDao.list(filter, start, limit);
    }

    @Override
    public void delete(Long id) {
        transactionDao.deleteById(id);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionDao.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return transactionDao.existsById(id);
    }

    @Override
    public ModelListWrapper<Transaction> listExpenses(Transaction filter, int start, int limit) {
        return transactionDao.listExpenses(filter, start, limit);
    }

    @Override
    public ModelListWrapper<Transaction> listIncomes(Transaction filter, int start, int limit) {
        return transactionDao.listIncomes(filter, start, limit);
    }

    @Override
    public double totalIncomes(Transaction tr) {
        return transactionDao.getTotalIncome(tr);
    }

    @Override
    public double totalExpenses(Transaction tr) {
        return transactionDao.getTotalExpense(tr);
    }
}
