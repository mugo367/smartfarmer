package com.smartfarmer.dao;

import com.smartfarmer.model.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDao implements DaoI<Activity> {

    Controller controller = new Controller();

    @Override
    public  boolean add(Activity activity) throws ParseException, SQLException {

        String query = "INSERT INTO activity(activityLabel, activityName, activityDescription, uid) VALUES('"+activity.getActivityLabel()+"','"+activity.getActivityName()+"'," +
                " '"+activity.getActivityDescription()+"', "+activity.getUid()+")";
        int result = controller.writeData(query);

        return  (result==1);
    }

    @Override
    public List<Activity> read(int id) throws SQLException, ParseException {
        List<Activity> activityList = new ArrayList<Activity>();

        String query="SELECT * FROM activity where uid="+id+"";

        ResultSet resultSet = controller.readData(query);
        while (resultSet.next()){
            Activity activity = new Activity();
            activity.setActivityLabel(resultSet.getString(2));
            activity.setActivityName(resultSet.getString(3));
            activity.setActivityDescription(resultSet.getString(4));
            activityList.add(activity);
        }
        return activityList;
    }

    @Override
    public boolean update(Activity activity) throws ParseException, SQLException {
        String query = " UPDATE activity SET activityName = '"+activity.getActivityName()+"', activityDescription ='"+activity.getActivityDescription()+"' " +
                "WHERE uid = "+activity.getUid()+" AND activityLabel = '"+activity.getActivityLabel()+"'";
        return controller.writeData(query)==1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        String query = "DELETE FROM activity WHERE activityLabel = '"+label+"' AND uid = "+id+"";
        return controller.writeData(query) ==1;
    }

}
