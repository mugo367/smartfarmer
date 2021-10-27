package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.model.ResultWrapper;

import java.util.Map;

public interface ProductionEjbI {
    ResultWrapper addProduction(Map<String, String[]> formData, int id);
    ResultWrapper listProductions(int id);
    void deleteProduction(String productions, int id);
}
