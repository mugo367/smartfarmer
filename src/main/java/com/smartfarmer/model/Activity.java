package com.smartfarmer.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="activities")
public class Activity implements Serializable {

    public Activity() {
    }

    public Activity( String activityLabel, String activityName, String activityDescription, int uid) {

        this.activityLabel = activityLabel;
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.uid = uid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String activityLabel;
    @Column
    private String activityName;
    @Column
    private String activityDescription;
    @Column
    private int uid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
