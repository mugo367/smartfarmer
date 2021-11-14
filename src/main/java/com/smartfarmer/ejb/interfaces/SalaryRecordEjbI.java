package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.entities.SalaryRecord;

public interface SalaryRecordEjbI extends EjbI<SalaryRecord>{
   double totalSalary();
}
