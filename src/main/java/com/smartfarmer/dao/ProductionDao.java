package com.smartfarmer.dao;


import com.smartfarmer.dao.interfaces.ProductionDaoI;
import com.smartfarmer.entities.Production;
import com.smartfarmer.util.ModelListWrapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductionDao extends DaoImplementation<Production, Long> implements ProductionDaoI {

    @Inject
    private EntityManager entityManager;

    public ProductionDao() {
        super(Production.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ModelListWrapper<Production> list(Production filter, int start, int limit) {
        ModelListWrapper<Production> results = new ModelListWrapper<>();

        String hql = "SELECT p FROM Production p WHERE p.id is not null";

        if (filter.getFieldId() != 0)
            hql += " AND p.fieldId=" + filter.getFieldId();

        Query query = entityManager.createQuery(hql, Production.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Production> productions = query.getResultList();

        results.setList(productions);
        results.setCount(this.count());

        return results;
    }
}