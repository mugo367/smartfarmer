package com.smartfarmer.dao;

import com.smartfarmer.bean.LoginBean;
import com.smartfarmer.dao.interfaces.LoginBeanDaoI;
import com.smartfarmer.model.Farmer;
import com.smartfarmer.util.EntityManager;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Named(value ="LoginBeanDao")
public class LoginBeanDao implements LoginBeanDaoI {
    @Inject
    EntityManager entityManager;

    //Check if user details exists in database
    public boolean checkUser(LoginBean login) throws Exception {
        String username = login.getUsername();
        String password = login.getPassword();

        Connection conn = entityManager.getConnection();

        if (username == null || password == null){
            return false;
        }else{
            String  query = "SELECT username, password FROM register WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            return  preparedStatement.executeQuery().next();
        }
    }

    @Override
    public Farmer getFarmerDetails(LoginBean login) throws SQLException {
        Connection conn = entityManager.getConnection();

        String query = "select * from register where username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, login.getUsername());
        Farmer farmer = new Farmer();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                farmer.setId(resultSet.getInt(1));
                farmer.setFullName(resultSet.getString(2));
                farmer.setUsername(resultSet.getString(3));
                farmer.setPhoneNumber(resultSet.getString(4));
                farmer.setEmailAddress(resultSet.getString(5));

                farmer.setCounty(resultSet.getString(7));
                farmer.setSubCounty(resultSet.getString(8));
                farmer.setFarmName(resultSet.getString(9));
                farmer.setFarmSize(resultSet.getDouble(10));
                farmer.setAdditionalInfo(resultSet.getString(11));
                return farmer;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return farmer;
    }

}
