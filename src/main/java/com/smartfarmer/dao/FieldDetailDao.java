package com.smartfarmer.dao;


import com.smartfarmer.dao.interfaces.FieldDetailDaoI;
import com.smartfarmer.entities.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;


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

    public Double getUsedFieldSize(int id){
        String query = "SELECT SUM(fieldSize) FROM field WHERE uid = ?";

        Double fieldSize = null;

        return fieldSize;
    }
}


