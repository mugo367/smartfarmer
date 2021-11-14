package com.smartfarmer.dao;


import com.smartfarmer.dao.interfaces.FieldDetailDaoI;
import com.smartfarmer.entities.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class FieldDetailDao extends DaoImplementation<Field, Long> implements FieldDetailDaoI {

    @Inject
    private javax.persistence.EntityManager entityManager;

    public FieldDetailDao() {
        super(Field.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public double getUsedFieldSize(){
        try {
            Query query =  entityManager.createQuery("SELECT SUM(f.fieldSize) FROM Field f");
            return (double) query.getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }

    @Override
    public void updateFieldStatus(long id) {

        Query query = entityManager.createQuery("update Field f set f.fieldStatus='Planted' where f.id=:fId");
        query.setParameter("fId", id);
        query.executeUpdate();
    }

    @Override
    public Field checkFieldStatus(long id) {
        try {
            Query query = entityManager.createQuery("SELECT f FROM Field f WHERE f.fieldStatus='Planted' AND f.id=:fId");
            query.setParameter("fId", id);

            return (Field) query.getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}


