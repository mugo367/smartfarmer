package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.EmployeeDaoI;
import com.smartfarmer.ejb.interfaces.EmployeeEjbI;
import com.smartfarmer.entities.Employee;
import com.smartfarmer.entities.enumFiles.Designation;
import com.smartfarmer.entities.enumFiles.EmpType;
import com.smartfarmer.entities.enumFiles.Gender;
import com.smartfarmer.model.Response;
import com.smartfarmer.util.AppException;
import com.smartfarmer.util.ModelListWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class EmployeeEjb implements EmployeeEjbI {
    @Inject
    EmployeeDaoI employeeDao;

    @Override
    public Response add(Employee employee) throws Exception {

        if (employee == null)
            throw new AppException("Invalid employee details!!");

        if(employee.getEmployeeGender() == null && employee.getEmployeeGenderStr() != null){
            employee.setEmployeeGender(Gender.valueOf(employee.getEmployeeGenderStr()));
        }

        if(employee.getEmployeeDesignation() == null && employee.getEmployeeDesignationStr() != null){
            employee.setEmployeeDesignation(Designation.valueOf(employee.getEmployeeDesignationStr()));
        }

        if(employee.getEmployeeType() == null && employee.getEmployeeTypeStr() != null){
            employee.setEmployeeType(EmpType.valueOf(employee.getEmployeeTypeStr()));
        }


        return new Response(true, "Successfully added",  employeeDao.save(employee));

    }

    @Override
    public Employee edit(Employee employee) {
        return employeeDao.edit(employee);
    }

    @Override
    public ModelListWrapper<Employee> list(Employee filter, int start, int limit) {
        return employeeDao.list(filter, start, limit);
    }

    @Override
    public void delete(Long id) {
        employeeDao.deleteById(id);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return employeeDao.existsById(id);
    }
}
