package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.TransactionDaoI;
import com.smartfarmer.entities.Transaction;
import com.smartfarmer.entities.enumFiles.TransactionType;
import com.smartfarmer.util.ModelListWrapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


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

    public double getTotalIncome(Transaction tr) {

        ModelListWrapper<Transaction> incomes = listIncomes(tr, 0,0);

        if (incomes != null) {
            Query q = entityManager.createQuery("SELECT SUM(t.transactionCost) FROM Transaction t WHERE t.transactionType=:transactionType");
            q.setParameter("transactionType", TransactionType.Income.name());

            return (double) q.getSingleResult();
        }
    return 0;
    }

    public double getTotalExpense(Transaction tr) {

        ModelListWrapper<Transaction> expenses = listExpenses(tr, 0,0);

        if (expenses != null) {
            Query query = entityManager.createQuery("SELECT SUM(t.transactionCost) FROM Transaction t WHERE t.transactionType=:transactionType");
            query.setParameter("transactionType", TransactionType.Expense.name());

            return (double) query.getSingleResult();
        }
        return 0;
    }

    @Override
    public ModelListWrapper<Transaction> listExpenses(Transaction filter, int start, int limit) {
        ModelListWrapper<Transaction> results = new ModelListWrapper<>();

        Query query = getEntityManager().createQuery("SELECT t FROM Transaction t WHERE t.id is not null and t.transactionType=:transactionType", Transaction.class);
        query.setParameter("transactionType", TransactionType.Expense.name());

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Transaction> resultList = query.getResultList();

        results.setList(resultList);
        results.setCount(this.count());

        return results;
    }

    @Override
    public ModelListWrapper<Transaction> listIncomes(Transaction filter, int start, int limit) {
        ModelListWrapper<Transaction> results = new ModelListWrapper<>();

        Query query = getEntityManager().createQuery("SELECT t FROM Transaction t WHERE t.id is not null and t.transactionType=:transactionType", Transaction.class);
        query.setParameter("transactionType", TransactionType.Income.name());

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Transaction> resultList = query.getResultList();

        results.setList(resultList);
        results.setCount(this.count());

        return results;
    }
}
