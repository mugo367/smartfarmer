package com.smartfarmer.model;

import com.smartfarmer.model.enumFiles.FieldStatus;

import java.io.Serializable;

public class Field implements Serializable {
    private int id;
    private String fieldLabel;
    private String fieldName;
    private Double fieldSize;
    private FieldStatus fieldStatus;
    private int uid;


    public Field() {
    }

    public Field( String fieldLabel, String fieldName, Double fieldSize, FieldStatus fieldStatus, int uid) {

        this.fieldLabel = fieldLabel;
        this.fieldName = fieldName;
        this.fieldSize = fieldSize;
        this.fieldStatus = fieldStatus;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Double getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(Double fieldSize) {
        this.fieldSize = fieldSize;
    }

    public FieldStatus getFieldStatus() {
        return fieldStatus;
    }

    public void setFieldStatus(FieldStatus fieldStatus) {
        this.fieldStatus = fieldStatus;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
