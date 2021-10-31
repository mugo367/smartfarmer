package com.smartfarmer.model;

import com.smartfarmer.model.enumFiles.Designation;
import com.smartfarmer.model.enumFiles.EmpType;
import com.smartfarmer.model.enumFiles.Gender;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table (name ="tbl_employees")
public class Employee extends BaseEntity implements Serializable {

    @Column(name="employee_number", unique = true)
    private String employeeNumber;

    @Column(name="employee_name")
    private String employeeName;

    @Column(name="id_number")
    private int idNumber;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender employeeGender;

    @Column(name="employee_email")
    private String employeeEmail;

    @Column(name="employee_contact")
    private String employeeContact;

    @Column(name="emergency_contact")
    private String employeeEmergencyContact;

    @Column(name="date_of_employment")
    @Temporal(TemporalType.DATE)
    private Date employeeDateOfEmp;

    @Column(name="employee_designation")
    @Enumerated(EnumType.STRING)
    private Designation employeeDesignation;

    @Column(name="employee_type")
    @Enumerated(EnumType.STRING)
    private EmpType employeeType;


    @Transient
    private String employeeGenderStr;
    @Transient
    private String employeeDesignationStr;
    @Transient
    private String employeeTypeStr;


}