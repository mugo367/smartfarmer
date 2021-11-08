package com.smartfarmer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name="tbl_scheduled_activities")
public class FieldTask extends BaseEntity{

    @ManyToOne(optional = false)
    private Activity activity;

    @Formula("(activity_id)")
    private long activityId;

    @Formula("(select a.activity_name from tbl_activities a where a.id=activity_id)")
    private String activityName;

    @ManyToOne(optional = false)
    private Field field;

    @Formula("(field_id)")
    private long fieldId;

    @Formula("(select f.field_name from tbl_fields f where f.id=field_id)")
    private String fieldName;

    @ManyToOne(optional = false)
    private Employee employee;

    @Formula("(employee_id)")
    private long employeeId;

    @Formula("(select e.employee_name from tbl_employees f where f.id=employee_id)")
    private String employeeName;

    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Future
    private Date endDate;

    @Column(name="description", columnDefinition = "LONGTEXT")
    private String activityDescription;

}
