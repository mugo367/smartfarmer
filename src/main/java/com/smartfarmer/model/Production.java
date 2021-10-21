package com.smartfarmer.model;

import com.smartfarmer.model.enumFiles.Unit;

import java.io.Serializable;
import java.util.Date;

public class Production implements Serializable {
    private int id;
    private String productionLabel;
    private Date productionDate;
    private int fieldId;
    private Double productionQuantity;
    private Unit unit;
    private String productionDetails;
    private int uid;

    private String fieldName;

    public Production() {
    }

    public Production( String productionLabel, Date productionDate, int fieldId, Double productionQuantity, Unit unit, String productionDetails, int uid) {
        this.productionLabel = productionLabel;
        this.productionDate = productionDate;
        this.fieldId = fieldId;
        this.productionQuantity = productionQuantity;
        this.unit = unit;
        this.productionDetails = productionDetails;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductionLabel() {
        return productionLabel;
    }

    public void setProductionLabel(String productionLabel) {
        this.productionLabel = productionLabel;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public Double getProductionQuantity() {
        return productionQuantity;
    }

    public void setProductionQuantity(Double productionQuantity) {
        this.productionQuantity = productionQuantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getProductionDetails() {
        return productionDetails;
    }

    public void setProductionDetails(String productionDetails) {
        this.productionDetails = productionDetails;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
