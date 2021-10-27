package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.model.ResultWrapper;

import java.util.Map;

public interface EmployeeEjbI {
    ResultWrapper addEmployee(Map<String, String[]> formData, int id);
    ResultWrapper listEmployees(int id);
    void deleteEmployees(String employees, int id);
}
