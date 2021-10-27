package com.smartfarmer.dao;

import com.smartfarmer.dao.interfaces.ActivityDaoI;
import com.smartfarmer.model.Activity;
import com.smartfarmer.util.EntityManager;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDao implements ActivityDaoI<Activity> {

    @Inject
    EntityManager entityManager;

    @Override
    public  boolean add(Activity activity) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();
        String query = "INSERT INTO activity(activityLabel, activityName, activityDescription, uid) " +
                "VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, activity.getActivityLabel());
        preparedStatement.setString(2, activity.getActivityName());
        preparedStatement.setString(3, activity.getActivityDescription());
        preparedStatement.setInt(4, activity.getUid());

        return  preparedStatement.executeUpdate() == 1;
    }

    @Override
    public List<Activity> read(int id) throws SQLException, ParseException {
        Connection conn = entityManager.getConnection();
        List<Activity> activityList = new ArrayList<>();

        String query="SELECT * FROM activity where uid =?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Activity activity = new Activity();
            activity.setId(resultSet.getInt(1));
            activity.setActivityLabel(resultSet.getString(2));
            activity.setActivityName(resultSet.getString(3));
            activity.setActivityDescription(resultSet.getString(4));
            activity.setUid(resultSet.getInt(5));
            activityList.add(activity);
        }
        return activityList;
    }

    @Override
    public boolean update(Activity activity) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();

        String query = " UPDATE activity SET activityName = ?, activityDescription = ? +" +
                "WHERE uid = ? AND activityLabel = ? ";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, activity.getActivityName());
        preparedStatement.setString(2, activity.getActivityDescription());
        preparedStatement.setInt(3, activity.getUid());
        preparedStatement.setString(4, activity.getActivityLabel());

        return preparedStatement.executeUpdate()==1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {

        Connection conn = entityManager.getConnection();
        String query = "DELETE FROM activity WHERE activityLabel = ? AND uid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, label);
        preparedStatement.setInt(2, id);
        return preparedStatement.executeUpdate() ==1;
    }

}
