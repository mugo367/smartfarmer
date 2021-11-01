package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.ActivityDaoI;
import com.smartfarmer.entities.Activity;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class ActivityDao extends DaoImplementation<Activity, Long> implements ActivityDaoI {

    @Inject
    private EntityManager entityManager;

    public ActivityDao() {
        super(Activity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
