package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.SalaryRecordDaoI;
import com.smartfarmer.ejb.interfaces.SalaryRecordEjbI;
import com.smartfarmer.entities.Employee;
import com.smartfarmer.entities.SalaryRecord;
import com.smartfarmer.model.Response;
import com.smartfarmer.util.ModelListWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class SalaryRecordEjb implements SalaryRecordEjbI {

   @PersistenceContext
   private EntityManager entityManager;

   @Inject
   private SalaryRecordDaoI salaryRecordDao;

   @Override
   public Response add(SalaryRecord salaryRecord) throws Exception {
      if (salaryRecord.getEmployeeId() > 0)
         salaryRecord.setEmployee(entityManager.find(Employee.class, salaryRecord.getEmployeeId()));

      if(salaryRecordDao.findByEmployeeId(salaryRecord.getEmployeeId()) !=null){
         return new Response(false, "Employee Record Already Exists",  salaryRecord);
      }

      return new Response(true, "Successfully added",  salaryRecordDao.save(salaryRecord));
   }

   @Override
   public SalaryRecord edit(SalaryRecord salaryRecord) {
      return  salaryRecordDao.edit(salaryRecord);
   }

   @Override
   public ModelListWrapper<SalaryRecord> list(SalaryRecord filter, int start, int limit) {
      return salaryRecordDao.list(filter, start, limit);
   }

   @Override
   public void delete(Long id) {
      salaryRecordDao.deleteById(id);
   }

   @Override
   public Optional<SalaryRecord> findById(Long id) {
      return salaryRecordDao.findById(id);
   }


   @Override
   public boolean existsById(Long id) {
      return salaryRecordDao.existsById(id);
   }

   @Override
   public double totalSalary() {
      return 0;
   }
}
