package com.smartfarmer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartfarmer.entities.enumFiles.Designation;
import com.smartfarmer.entities.enumFiles.EmpType;
import com.smartfarmer.entities.enumFiles.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table (name ="tbl_employees")
public class Employee extends BaseEntity {

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

    @Getter(onMethod_ = @JsonIgnore)
    @Transient
    private String employeeGenderStr;

    @Getter(onMethod_ = @JsonIgnore)
    @Transient
    private String employeeDesignationStr;

    @Getter(onMethod_ = @JsonIgnore)
    @Transient
    private String employeeTypeStr;


}
