package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.model.ResultWrapper;

import java.util.Map;

public interface EquipmentEjbI {
    ResultWrapper addEquipment(Map<String, String[]> formData, int id);
    ResultWrapper listEquipments(int id);
    void deleteEquipments(String equipment, int id);
}
