package com.smartfarmer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name="tbl_salaries")
public class SalaryRecord extends BaseEntity {

   @OneToOne(optional = false)
   private Employee employee;

   @Formula("(employee_id)")
   private long employeeId;

   @Formula("(select e.employee_name from tbl_employees e where e.id=employee_id)")
   private String employeeName;

   @Column
   private double salary;

   @Column(name="description", columnDefinition = "LONGTEXT")
   private String info;

}
