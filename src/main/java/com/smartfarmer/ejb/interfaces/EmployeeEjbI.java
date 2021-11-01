package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.entities.Employee;

import java.util.Optional;

public interface EmployeeEjbI {
    Employee addEmployee(Employee employee) throws Exception;
    Employee editEmployee(Employee employee);
    ModelListWrapper<Employee> listEmployees(Employee filter, int start, int limit);
    void deleteEmployee(Long id);
    Optional<Employee> findById(Long id);
}
