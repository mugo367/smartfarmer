package com.smartfarmer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name ="tbl_activities")
public class Activity extends BaseEntity implements Serializable  {

    @Column(name="activity_label")
    private String activityLabel;

    @Column(name="activity_name")
    private String activityName;

    @Column(name="description", columnDefinition = "LONGTEXT")
    private String activityDescription;

}
