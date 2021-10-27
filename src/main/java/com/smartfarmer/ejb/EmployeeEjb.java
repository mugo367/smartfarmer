package com.smartfarmer.ejb;

import com.google.gson.Gson;
import com.smartfarmer.dao.interfaces.EmployeeDaoI;
import com.smartfarmer.ejb.interfaces.EmployeeEjbI;
import com.smartfarmer.model.Employee;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.model.enumFiles.Designation;
import com.smartfarmer.model.enumFiles.EmpType;
import com.smartfarmer.model.enumFiles.Gender;
import com.smartfarmer.util.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class EmployeeEjb implements EmployeeEjbI {
    @Inject
    EmployeeDaoI<Employee> employeeDao;
    @Override
    public ResultWrapper addEmployee(Map<String, String[]> formData, int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        Employee employee = new Employee();
        try {
            BeanUtils.populate(employee, formData);

            if(employee.getEmployeeGender() == null && employee.getEmployeeGenderStr() != null){
                employee.setEmployeeGender(Gender.valueOf(employee.getEmployeeGenderStr()));
            }

            if(employee.getEmployeeDesignation() == null && employee.getEmployeeDesignationStr() != null){
                employee.setEmployeeDesignation(Designation.valueOf(employee.getEmployeeDesignationStr()));
            }

            if(employee.getEmployeeType() == null && employee.getEmployeeTypeStr() != null){
                employee.setEmployeeType(EmpType.valueOf(employee.getEmployeeTypeStr()));
            }

            employee.setUid(id);

        } catch (Exception e) {
            e.printStackTrace();
            resultWrapper.setMessage(e.getMessage());
            employee = null;
        }
        if (employee == null){
            resultWrapper.setSuccess(false);
            return resultWrapper;
        }
        try {
            employeeDao.add(employee);
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        return resultWrapper;
    }

    @Override
    public ResultWrapper listEmployees(int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        List<Employee> employeeList = new ArrayList<>();

        try {
            employeeList = employeeDao.read(id);

        } catch (SQLException | ParseException e ) {
            e.printStackTrace();
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        resultWrapper.setList(employeeList);
        return resultWrapper;
    }

    @Override
    public void deleteEmployees(String employees, int id) {
        try {
            List<String> employeeNumbers = new Gson().fromJson( employees, List.class );

            for(String employeeNumber : employeeNumbers) {
                employeeDao.delete(employeeNumber, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
