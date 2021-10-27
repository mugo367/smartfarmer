package com.smartfarmer.dao.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeDaoI <Employee> {
    boolean add(Employee employee) throws ParseException, SQLException;
    List<Employee> read(int id) throws SQLException, ParseException;
    boolean update(Employee employee) throws ParseException, SQLException;
    boolean delete(String label, int id) throws ParseException, SQLException;
}