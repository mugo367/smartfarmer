package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.LoginBeanDaoI;
import com.smartfarmer.entities.Farmer;
import com.smartfarmer.model.LoginBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public class LoginBeanDao implements LoginBeanDaoI {

    @PersistenceContext
    private EntityManager entityManager;

    public Farmer getUser(LoginBean loginBean) {
        try {
            Query q = entityManager.createQuery("SELECT f FROM Farmer f WHERE (f.emailAddress = :username OR f.username = :username) AND f.password = :password");
            q.setParameter("username", loginBean.getUsername());
            q.setParameter("password", loginBean.getPassword());
            return (Farmer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

}
