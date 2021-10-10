package com.smartfarmer.model;


import com.smartfarmer.model.enumFiles.Condition;

import java.io.Serializable;

public class Equipment implements Serializable {
    private int id;
    private String equipmentLabel;
    private String equipmentName;
    private Condition equipmentCondition;
    private String equipmentQuantity;
    private int uid;

    public Equipment() {
    }

    public Equipment( String equipmentLabel, String equipmentName, Condition equipmentCondition, String equipmentQuantity, int uid) {
        this.equipmentLabel = equipmentLabel;
        this.equipmentName = equipmentName;
        this.equipmentCondition = equipmentCondition;
        this.equipmentQuantity = equipmentQuantity;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentLabel() {
        return equipmentLabel;
    }

    public void setEquipmentLabel(String equipmentLabel) {
        this.equipmentLabel = equipmentLabel;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Condition getEquipmentCondition() {
        return equipmentCondition;
    }

    public void setEquipmentCondition(Condition equipmentCondition) {
        this.equipmentCondition = equipmentCondition;
    }

    public String getEquipmentQuantity() {
        return equipmentQuantity;
    }

    public void setEquipmentQuantity(String equipmentQuantity) {
        this.equipmentQuantity = equipmentQuantity;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
