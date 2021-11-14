package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.FieldTaskDaoI;
import com.smartfarmer.ejb.interfaces.FieldTaskEjbI;
import com.smartfarmer.entities.Activity;
import com.smartfarmer.entities.Employee;
import com.smartfarmer.entities.Field;
import com.smartfarmer.entities.FieldTask;
import com.smartfarmer.model.Response;
import com.smartfarmer.util.AppException;
import com.smartfarmer.util.ModelListWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class FieldTaskEjb implements FieldTaskEjbI {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private FieldTaskDaoI fieldTaskDao;

    @Override
    public Response add(FieldTask fieldTask) throws Exception {

        if (fieldTask == null)
            throw new AppException("Invalid FieldTask details!!");

        if (fieldTask.getFieldId() > 0)
            fieldTask.setField(entityManager.find(Field.class, fieldTask.getFieldId()));

        if (fieldTask.getActivityId() > 0)
            fieldTask.setActivity(entityManager.find(Activity.class, fieldTask.getActivityId()));

        if (fieldTask.getEmployeeId() > 0)
            fieldTask.setEmployee(entityManager.find(Employee.class, fieldTask.getEmployeeId()));

        return new Response(true, "Successfully added",  fieldTaskDao.save(fieldTask));
    }

    @Override
    public FieldTask edit(FieldTask fieldTask) {

        return  fieldTaskDao.edit(fieldTask);
    }

    @Override
    public ModelListWrapper<FieldTask> list(FieldTask filter, int start, int limit) {
        return fieldTaskDao.list(filter, start, limit);
    }

    @Override
    public void delete(Long id) {
        fieldTaskDao.deleteById(id);
    }

    @Override
    public Optional<FieldTask> findById(Long id) {
        return fieldTaskDao.findById(id);
    }


    @Override
    public boolean existsById(Long id) {
        return fieldTaskDao.existsById(id);
    }


}
