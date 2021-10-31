package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.entities.Farmer;
import com.smartfarmer.model.ResultWrapper;

import java.util.Map;

public interface FieldDetailEjbI {
    ResultWrapper addField(Map<String, String[]> formData, int id, Farmer farmerDetails);
    ResultWrapper listFields(int id);
    void deleteField(String field, int id);
}
