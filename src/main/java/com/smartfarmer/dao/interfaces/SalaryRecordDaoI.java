package com.smartfarmer.dao.interfaces;

import com.smartfarmer.entities.SalaryRecord;

public interface SalaryRecordDaoI extends DaoI<SalaryRecord, Long> {
   double getTotalSalary();
   SalaryRecord findByEmployeeId(long id);
}
