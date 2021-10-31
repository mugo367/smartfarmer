package com.smartfarmer.dao;

import com.smartfarmer.entities.Activity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ActivityDao implements GenericDaoI<Activity> {

    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public Activity save(Activity activity) {
        entityManager.persist(activity);
        entityManager.flush();
        return activity;
    }

    @Override
    public Boolean delete(Activity activity) throws Exception {
        entityManager.remove(activity);
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ModelListWrapper<Activity> list(Activity filter, int start, int limit) {
        ModelListWrapper<Activity> results = new ModelListWrapper<>();

        String hql = "SELECT a FROM Activity a WHERE a.id is not null";
        Query query = entityManager.createQuery(hql, Activity.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<Activity> resultList = query.getResultList();

        results.setList(resultList);

        return results;
    }

    @Override
    public Activity edit(Activity activity) throws Exception {
        return null;
    }

    @Override
    public Activity find(int tId) {
        return null;
    }
}
