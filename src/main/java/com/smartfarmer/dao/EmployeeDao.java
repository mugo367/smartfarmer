package com.smartfarmer.dao;


import com.smartfarmer.model.Employee;
import com.smartfarmer.model.enumFiles.Designation;
import com.smartfarmer.model.enumFiles.EmpType;
import com.smartfarmer.model.enumFiles.Gender;
import com.smartfarmer.util.Controller;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Named(value ="EmployeeDao")
public class EmployeeDao implements DaoI<Employee> {
    @Inject
    Controller controller;
    @Override
    public boolean add(Employee employee) throws ParseException, SQLException {

        String query = "INSERT INTO employees(employeeNumber, employeeName, idNumber, employeeGender, employeeEmail, employeeContact, employeeEmergencyContact, employeeDateOfEmp, employeeDesignation, employeeType, uid) " +
                "VALUES('"+employee.getEmployeeNumber()+"', '"+employee.getEmployeeName()+"', "+employee.getIdNumber()+", '"+employee.getEmployeeGender()+"', '"+employee.getEmployeeEmail()+"', '"+employee.getEmployeeContact()+"' " +
                ", '"+employee.getEmployeeContact()+"', '"+new java.sql.Date(employee.getEmployeeDateOfEmp().getTime())+"', '"+employee.getEmployeeDesignation()+"', '"+employee.getEmployeeType()+"',"+employee.getUid()+" )";
        return  controller.writeData(query) == 1;
    }

    @Override
    public List<Employee> read(int id) throws ParseException, SQLException{
        List<Employee> employeeList = new ArrayList<>();

        String query="SELECT * FROM employees WHERE uid = "+id+"";
        ResultSet resultSet = controller.readData(query);
        while (resultSet.next()){
            Employee employee = new Employee();
            employee.setEmployeeNumber(resultSet.getString(2));
            employee.setEmployeeName(resultSet.getString(3));
            employee.setIdNumber(resultSet.getInt(4));
            employee.setEmployeeGender(Gender.valueOf(resultSet.getString(5)));
            employee.setEmployeeEmail(resultSet.getString(6));
            employee.setEmployeeContact(resultSet.getString(7));
            employee.setEmployeeEmergencyContact(resultSet.getString(8));
            employee.setEmployeeDateOfEmp(new java.util.Date(resultSet.getDate(9).getTime()));
            employee.setEmployeeDesignation(Designation.valueOf(resultSet.getString(10)));
            employee.setEmployeeType(EmpType.valueOf(resultSet.getString(11)));
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public boolean update(Employee employee) throws ParseException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        String query = "DELETE FROM employees WHERE uid = "+id+", employeeNumber ="+label+"";
        return controller.writeData(query)==1;
    }

}
