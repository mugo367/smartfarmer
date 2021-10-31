package com.smartfarmer.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_farmers")
public class Farmer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "phone_no")
    private String phoneNumber;

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

    @Column(name = "additionalInfo")
    private String additionalInfo;

    @OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
    private List<Production> productions = new ArrayList<>();
    @OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
    private List<Field> fields = new ArrayList<>();
    @OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();
    @OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();
    @OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
    private List<Activity> activities = new ArrayList<>();


    public Farmer(int id, String fullName, String username, String phoneNumber, String emailAddress, String password, String county, String subCounty, String farmName, Double farmSize, String additionalInfo) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.county = county;
        this.subCounty = subCounty;
        this.farmName = farmName;
        this.farmSize = farmSize;
        this.additionalInfo = additionalInfo;
    }

}
