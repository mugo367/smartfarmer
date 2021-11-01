package com.smartfarmer.dao;


import com.smartfarmer.dao.interfaces.EmployeeDaoI;
import com.smartfarmer.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class EmployeeDao extends DaoImplementation<Employee, Long> implements EmployeeDaoI {

    @Inject
    private javax.persistence.EntityManager entityManager;

    public EmployeeDao() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
