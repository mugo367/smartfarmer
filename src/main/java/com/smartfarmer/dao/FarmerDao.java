package com.smartfarmer.dao;

import com.smartfarmer.entities.Farmer;
import com.smartfarmer.util.EntityManager;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
@Named(value ="FarmerDao")
public class FarmerDao {
    @Inject
    EntityManager entityManager;

    public boolean add(Farmer farmer) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();

        String query = "INSERT INTO register(fullName, username, phoneNumber, emailAddress, password, county, subCounty, farmName, farmSize, additionalInfo) " +
                " VALUES(?, ?, ?, ?, ?, ?, ?,?,?,?)";


        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, farmer.getFullName());
        preparedStatement.setString(2, farmer.getUsername());
        preparedStatement.setString(3, farmer.getPhoneNumber());
        preparedStatement.setString(4, farmer.getEmailAddress());
        preparedStatement.setString(5, farmer.getPassword());
        preparedStatement.setString(6, farmer.getCounty());
        preparedStatement.setString(7, farmer.getSubCounty());
        preparedStatement.setString(8, farmer.getFarmName());
        preparedStatement.setDouble(9, farmer.getFarmSize());
        preparedStatement.setString(10, farmer.getAdditionalInfo());

        return  preparedStatement.executeUpdate() == 1;
    }

    public List<Farmer> read() throws SQLException, ParseException {
        return null;
    }

    public boolean delete(Farmer farmer) throws SQLException {
        return false;
    }


}
