package com.smartfarmer.dao;


import com.smartfarmer.model.Employee;
import com.smartfarmer.model.enumFiles.Designation;
import com.smartfarmer.model.enumFiles.EmpType;
import com.smartfarmer.model.enumFiles.Gender;
import com.smartfarmer.util.Controller;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Connection con = controller.getConnection();
        String query = "INSERT INTO employees(employeeNumber, employeeName, idNumber, employeeGender, employeeEmail, employeeContact, employeeEmergencyContact, employeeDateOfEmp, employeeDesignation, employeeType, uid) " +
                "VALUES(?, ?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);

        preparedStatement.setString(1, employee.getEmployeeNumber());
        preparedStatement.setString(2, employee.getEmployeeName());
        preparedStatement.setInt(3, employee.getIdNumber());
        preparedStatement.setString(4, employee.getEmployeeGender().name());
        preparedStatement.setString(5, employee.getEmployeeEmail());
        preparedStatement.setString(6, employee.getEmployeeContact());
        preparedStatement.setString(7, employee.getEmployeeEmergencyContact());
        preparedStatement.setDate(8, new java.sql.Date(employee.getEmployeeDateOfEmp().getTime()));
        preparedStatement.setString(9, employee.getEmployeeDesignation().name());
        preparedStatement.setString(10, employee.getEmployeeType().name());
        preparedStatement.setInt(11, employee.getUid());


        return  preparedStatement.executeUpdate() == 1;
    }

    @Override
    public List<Employee> read(int id) throws ParseException, SQLException{
        Connection con = controller.getConnection();
        List<Employee> employeeList = new ArrayList<>();

        String query="SELECT * FROM employees WHERE uid = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Employee employee = new Employee();
            employee.setId(resultSet.getInt(1));
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
            employee.setUid(resultSet.getInt(12));
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
        Connection conn = controller.getConnection();
        String query = "DELETE FROM employees WHERE uid = ? AND employeeNumber = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, label);
        return preparedStatement.executeUpdate() ==1;
    }

}
