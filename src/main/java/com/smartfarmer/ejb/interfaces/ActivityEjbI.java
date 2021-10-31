package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.dao.ModelListWrapper;
import com.smartfarmer.entities.Activity;

public interface ActivityEjbI {
    Activity addActivity(Activity activity) throws Exception;
    ModelListWrapper<Activity> listActivities(Activity filter, int start, int limit);
    void deleteActivities(String activities, int id);
}
