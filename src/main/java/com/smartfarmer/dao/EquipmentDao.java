package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.EquipmentDaoI;
import com.smartfarmer.entities.Equipment;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class EquipmentDao extends DaoImplementation<Equipment, Long> implements EquipmentDaoI {

    @Inject
    private javax.persistence.EntityManager entityManager;

    public EquipmentDao() {
        super(Equipment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}