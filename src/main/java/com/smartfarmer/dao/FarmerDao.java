package com.smartfarmer.dao;

import com.smartfarmer.model.Farmer;
import com.smartfarmer.util.Controller;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
@Named(value ="FarmerDao")
public class FarmerDao {
    @Inject
    Controller controller;

    public boolean add(Farmer farmer) throws ParseException, SQLException {

        String query = "INSERT INTO register(fullName, username, phoneNumber, emailAddress, password, county, subCounty, farmName, farmSize, additionalInfo) VALUES(" +
                "'"+farmer.getFullName()+"', '"+farmer.getUsername()+"', '"+ farmer.getPhoneNumber()+"', '"+ farmer.getEmailAddress()+"', '"+farmer.getPassword()+"'," +
                "'"+farmer.getCounty()+"' , '"+farmer.getSubCounty()+"' , '"+farmer.getFarmName()+"', "+farmer.getFarmSize()+", '"+farmer.getAdditionalInfo()+"')";
        return controller.writeData(query)==1;
    }

    public List<Farmer> read() throws SQLException, ParseException {
        return null;
    }

    public boolean delete(Farmer farmer) throws SQLException {
        return false;
    }


}
