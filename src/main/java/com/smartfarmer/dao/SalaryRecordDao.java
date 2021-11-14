package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.SalaryRecordDaoI;
import com.smartfarmer.entities.SalaryRecord;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SalaryRecordDao extends DaoImplementation<SalaryRecord, Long> implements SalaryRecordDaoI {

   @Inject
   private javax.persistence.EntityManager entityManager;

   public SalaryRecordDao() {
      super(SalaryRecord.class);
   }

   @Override
   protected EntityManager getEntityManager() {
      return entityManager;
   }

   public double getTotalSalary() {

       try{
         Query query = entityManager.createQuery("SELECT SUM(t.salary) FROM SalaryRecord t");

         return (double) query.getSingleResult();
      }catch (Exception e) {
          e.printStackTrace();
          return 0;
       }

   }

   @Override
   public SalaryRecord findByEmployeeId(long id) {
      Query query = entityManager.createQuery("SELECT s FROM SalaryRecord s WHERE s.employeeId=:employeeId");
      query.setParameter("employeeId", id);
      return (SalaryRecord) query.getSingleResult();
   }
}