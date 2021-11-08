package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.FieldTaskDaoI;
import com.smartfarmer.entities.FieldTask;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class FieldTaskDao  extends DaoImplementation<FieldTask, Long> implements FieldTaskDaoI {
    @Inject
    private EntityManager entityManager;

    public FieldTaskDao() {
        super(FieldTask.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
