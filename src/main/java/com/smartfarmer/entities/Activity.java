package com.smartfarmer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name ="tbl_activities")
public class Activity extends BaseEntity {

    @Column(name="activity_label")
    private String activityLabel;

    @Column(name="activity_name")
    private String activityName;

    @Column(name="description", columnDefinition = "LONGTEXT")
    private String activityDescription;

}
