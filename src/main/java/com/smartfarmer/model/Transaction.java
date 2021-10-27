package com.smartfarmer.model;

import com.smartfarmer.model.enumFiles.TransactionType;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private int id;
    private Date transactionDate;
    private TransactionType transactionType;
    private String transactionTypeStr;
    private String transactionLabel;
    private double costPerUnit;
    private int units;
    private double transactionCost;
    private String transactionDetails;
    private int uid;

    public Transaction() {
    }

    public Transaction(Date transactionDate, TransactionType transactionType, String transactionLabel, double costPerUnit, int units, double transactionCost, String transactionDetails, int uid) {
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionLabel = transactionLabel;
        this.costPerUnit = costPerUnit;
        this.units = units;
        this.transactionCost = transactionCost;
        this.transactionDetails = transactionDetails;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionLabel() {
        return transactionLabel;
    }

    public void setTransactionLabel(String transactionLabel) {
        this.transactionLabel = transactionLabel;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getTransactionCost() {
        return transactionCost;
    }

    public String getTransactionTypeStr() {
        return transactionTypeStr;
    }

    public void setTransactionTypeStr(String transactionTypeStr) {
        this.transactionTypeStr = transactionTypeStr;
    }

    public void setTransactionCost(double transactionCost) {
        this.transactionCost = transactionCost;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
