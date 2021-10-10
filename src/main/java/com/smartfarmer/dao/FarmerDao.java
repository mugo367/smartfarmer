package com.smartfarmer.dao;

import com.smartfarmer.model.Farmer;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class FarmerDao {
    Controller controller = new Controller();

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
