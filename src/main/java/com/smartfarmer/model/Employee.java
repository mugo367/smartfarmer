package com.smartfarmer.model;


import com.smartfarmer.model.enumFiles.Designation;
import com.smartfarmer.model.enumFiles.EmpType;
import com.smartfarmer.model.enumFiles.Gender;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private int id;
    private String employeeNumber;
    private String employeeName;
    private int idNumber;
    private Gender employeeGender;
    private String employeeEmail;
    private String employeeContact;
    private String employeeEmergencyContact;
    private Date employeeDateOfEmp;
    private Designation employeeDesignation;
    private EmpType employeeType;
     private int uid;

    public Employee() {

    }

    public Employee( String employeeNumber, String employeeName, int idNumber, Gender employeeGender, String employeeEmail, String employeeContact, String employeeEmergencyContact, Date employeeDateOfEmp, Designation employeeDesignation, EmpType employeeType, int uid) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.idNumber = idNumber;
        this.employeeGender = employeeGender;
        this.employeeEmail = employeeEmail;
        this.employeeContact = employeeContact;
        this.employeeEmergencyContact = employeeEmergencyContact;
        this.employeeDateOfEmp = employeeDateOfEmp;
        this.employeeDesignation = employeeDesignation;
        this.employeeType = employeeType;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public Gender getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(Gender employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    public String getEmployeeEmergencyContact() {


        return employeeEmergencyContact;
    }

    public void setEmployeeEmergencyContact(String employeeEmergencyContact) {
        this.employeeEmergencyContact = employeeEmergencyContact;
    }

    public Date getEmployeeDateOfEmp() {

        return employeeDateOfEmp;
    }

    public void setEmployeeDateOfEmp(Date employeeDateOfEmp) {
        this.employeeDateOfEmp = employeeDateOfEmp;
    }

    public Designation getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(Designation employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public EmpType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmpType employeeType) {
        this.employeeType = employeeType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
