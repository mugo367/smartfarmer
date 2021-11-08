package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.TransactionDaoI;
import com.smartfarmer.entities.Transaction;
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

    public Double getTotalIncome(int id) {

        Double totalIncome = null;

        return totalIncome;

    }

    public Double getTotalExpense(int id) {

        Double totalExpense = null;

        return totalExpense;

    }

    @Override
    public ModelListWrapper<Transaction> list(Transaction filter, int start, int limit) {

        ModelListWrapper<Transaction> results = new ModelListWrapper<>();
        String hql = "SELECT t FROM Transaction t WHERE t.id is not null";


        Query query = getEntityManager().createQuery(hql, Transaction.class);

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
