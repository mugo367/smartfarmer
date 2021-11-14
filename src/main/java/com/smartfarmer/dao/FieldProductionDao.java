package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.FieldProductionDaoI;
import com.smartfarmer.entities.FieldProduction;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class FieldProductionDao extends DaoImplementation<FieldProduction, Long> implements FieldProductionDaoI {

   @Inject
   private javax.persistence.EntityManager entityManager;

   public FieldProductionDao() {
      super(FieldProduction.class);
   }

   @Override
   protected EntityManager getEntityManager() {
      return entityManager;
   }

}
