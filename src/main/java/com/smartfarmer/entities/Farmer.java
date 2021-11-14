package com.smartfarmer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_farmers")
public class Farmer implements Serializable {

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "phone_no")
    private String phoneNumber;

    @Email
    @Column(name = "email_address")
    private String emailAddress;

    @Column
    private String password;

    @Column
    private String county;

    @Column
    private String subCounty;

    @Column(name = "farm_name")
    private String farmName;

    @Column(name = "full_size")
    private Double farmSize;

    @Column(name = "unit")
    private String unit;

    @Column(name = "additionalInfo")
    private String additionalInfo;



}
