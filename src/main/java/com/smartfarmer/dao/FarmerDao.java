package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.FarmerDaoI;
import com.smartfarmer.entities.Farmer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class FarmerDao extends DaoImplementation<Farmer, Long> implements FarmerDaoI {

    @Inject
    EntityManager entityManager;

    public FarmerDao() {
        super(Farmer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Farmer add(Farmer farmer) {

        return getEntityManager().merge(farmer);
    }

    @Override
    public List<Farmer> read() {

        Query query = getEntityManager().createQuery("SELECT f FROM Farmer f WHERE f.id is not null", Farmer.class);

        List<Farmer> resultList = query.getResultList();
        return resultList;
    }


    @Override
    public Farmer findUsername(Farmer farmer) {

        Query query = getEntityManager().createQuery("SELECT f FROM Farmer f WHERE (f.emailAddress = :username OR f.username = :username)", Farmer.class);
        query.setParameter("username", farmer.getUsername());
        return (Farmer) query.getSingleResult();

    }

    @Override
    public double getTotalFarmSize() {
        Query query = getEntityManager().createQuery("SELECT f.farmSize FROM Farmer f");
        return (double) query.getSingleResult();
    }


}
