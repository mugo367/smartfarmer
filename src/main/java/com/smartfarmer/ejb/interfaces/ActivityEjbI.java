package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.model.ResultWrapper;

import java.util.Map;

public interface ActivityEjbI {
    ResultWrapper addActivity(Map<String, String[]> formData, int id);
    ResultWrapper listActivities(int id);
    void deleteActivities(String activities, int id);
}
