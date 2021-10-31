package com.smartfarmer.ejb;

import com.smartfarmer.dao.GenericDaoI;
import com.smartfarmer.dao.ModelListWrapper;
import com.smartfarmer.ejb.interfaces.ActivityEjbI;
import com.smartfarmer.entities.Activity;
import com.smartfarmer.entities.Farmer;
import com.smartfarmer.util.AppException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ActivityEjb implements ActivityEjbI {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    GenericDaoI<Activity> activityDao;

    @Override
    public Activity addActivity(Activity activity) throws Exception {

        if (activity == null)
            throw new AppException("Invalid item details!!");

        if(activity.getActivityLabel() == null || activity.getActivityName() == null || activity.getActivityDescription() == null){
            throw new AppException("Label, Name, Description are required!");
        }

        if (activity.getUid() > 0)
            activity.setFarmer(entityManager.find(Farmer.class, activity.getUid()));

        return  activity;
    }

    @Override
    public ModelListWrapper<Activity> listActivities(Activity filter, int start, int limit) {
       return activityDao.list(filter, start, limit);
    }

    @Override
    public void deleteActivities(String activities, int id) {

    }
}
