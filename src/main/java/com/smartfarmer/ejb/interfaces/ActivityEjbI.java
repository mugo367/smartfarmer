package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.entities.Activity;

import java.util.Optional;

public interface ActivityEjbI {
    Activity addActivity(Activity activity) throws Exception;
    Activity editActivity(Activity activity);
    ModelListWrapper<Activity> listActivities(Activity filter, int start, int limit);
    void deleteActivity(Long id);
    Optional<Activity> findById(Long id);
}
