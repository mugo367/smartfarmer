package com.smartfarmer.dao;


import com.smartfarmer.dao.interfaces.ProductionDaoI;
import com.smartfarmer.entities.Production;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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

}