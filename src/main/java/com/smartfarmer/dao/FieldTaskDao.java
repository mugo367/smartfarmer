package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.FieldTaskDaoI;
import com.smartfarmer.entities.FieldTask;
import com.smartfarmer.util.ModelListWrapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    @SuppressWarnings("unchecked")
    public ModelListWrapper<FieldTask> list(FieldTask filter, int start, int limit) {
        ModelListWrapper<FieldTask> results = new ModelListWrapper<>();

        String hql = "SELECT f FROM FieldTask f WHERE f.id is not null";
        if (filter.getActivityId() != 0)
            hql += " AND f.activityId=" + filter.getActivityId();

        if (filter.getFieldId() != 0)
            hql += " AND f.fieldId=" + filter.getFieldId();

        if (filter.getEmployeeId() != 0)
            hql += " AND f.employeeId=" + filter.getEmployeeId();

        Query query = entityManager.createQuery(hql, FieldTask.class);

        if (start > 0)
            query.setFirstResult(start);

        if (limit > 0)
            query.setMaxResults(limit);

        List<FieldTask> tasks = query.getResultList();

        results.setList(tasks);
        results.setCount(this.count());

        return results;
    }

}
