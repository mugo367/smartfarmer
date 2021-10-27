package com.smartfarmer.ejb;

import com.google.gson.Gson;
import com.smartfarmer.dao.interfaces.ActivityDaoI;
import com.smartfarmer.ejb.interfaces.ActivityEjbI;
import com.smartfarmer.model.Activity;
import com.smartfarmer.model.ResultWrapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class ActivityEjb implements ActivityEjbI {
    @Inject
    ActivityDaoI<Activity> activityDao;

    @Override
    public ResultWrapper addActivity(Map<String, String[]> formData, int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        Activity activity = new Activity();
        try {
            BeanUtils.populate(activity, formData);
            activity.setUid(id);
        } catch (Exception ex) {
            resultWrapper.setMessage(ex.getMessage());
            ex.printStackTrace();
            activity = null;
        }

        if (activity == null){
            resultWrapper.setSuccess(false);
            return resultWrapper;
        }
        if(activity.getActivityLabel() == null || activity.getActivityName() == null || activity.getActivityDescription() == null){
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage("Label, Name, Description are required!");
            return resultWrapper;
        }
        try {
            activityDao.add(activity);
        } catch (ParseException | SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        return  resultWrapper;
    }

    @Override
    public ResultWrapper listActivities(int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        List<Activity> activityList = new ArrayList<>();

        try {
            activityList = activityDao.read(id);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        resultWrapper.setList(activityList);
        return resultWrapper;
    }

    @Override
    public void deleteActivities(String activities, int id) {
        try {
            List<String> transactionLabels = new Gson().fromJson( activities, List.class );

            for(String transactionLabel : transactionLabels) {
                activityDao.delete(transactionLabel, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
