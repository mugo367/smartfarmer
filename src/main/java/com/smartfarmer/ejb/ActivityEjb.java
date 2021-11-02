package com.smartfarmer.ejb;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.dao.interfaces.ActivityDaoI;
import com.smartfarmer.ejb.interfaces.ActivityEjbI;
import com.smartfarmer.entities.Activity;
import com.smartfarmer.util.AppException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class ActivityEjb implements ActivityEjbI {


    @Inject
    private ActivityDaoI activityDao;

    @Override
    public Activity add(Activity activity) throws Exception {

        if (activity == null)
            throw new AppException("Invalid activity details!!");

        if(activity.getActivityLabel() == null || activity.getActivityName() == null || activity.getActivityDescription() == null){
            throw new AppException("Label, Name, Description are required!");
        }

        return  activityDao.save(activity);
    }

    @Override
    public Activity edit(Activity activity) {

        return  activityDao.edit(activity);
    }

    @Override
    public ModelListWrapper<Activity> list(Activity filter, int start, int limit) {
       return activityDao.list(filter, start, limit);
    }

    @Override
    public void delete(Long id) {
        activityDao.deleteById(id);
    }

    @Override
    public Optional<Activity> findById(Long id) {
        return activityDao.findById(id);
    }


    @Override
    public boolean existsById(Long id) {
        return activityDao.existsById(id);
    }


}
