package com.smartfarmer.dao;


import com.smartfarmer.model.Field;
import com.smartfarmer.model.enumFiles.FieldStatus;
import com.smartfarmer.util.Controller;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class FieldDetailDao implements FieldDetailDaoI {
    @Inject
    Controller controller;
    @Override
    public boolean add(Field field) throws ParseException, SQLException {
        Connection conn = controller.getConnection();

        String query = "INSERT INTO field(fieldLabel, fieldName, fieldSize, fieldStatus, uid) " +
                "VALUES(?,?,?,?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, field.getFieldLabel());
        preparedStatement.setString(2, field.getFieldName());
        preparedStatement.setDouble(3, field.getFieldSize());
        preparedStatement.setString(4, field.getFieldStatus().name());
        preparedStatement.setInt(5, field.getUid());

        return  preparedStatement.executeUpdate() == 1;
    }

    @Override
    public List<Field> read(int id) throws SQLException, ParseException {
        List<Field> fieldList = new ArrayList<>();
        Connection conn = controller.getConnection();

        String query = "SELECT * FROM field WHERE uid =?" ;
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
           Field field = new Field();
           field.setId(resultSet.getInt(1));
           field.setFieldLabel(resultSet.getString(2));
           field.setFieldName(resultSet.getString(3));
           field.setFieldSize(resultSet.getDouble(4));
           field.setFieldStatus(FieldStatus.valueOf(resultSet.getString(5)));
            field.setUid(resultSet.getInt(6));
           fieldList.add(field);
        }
        return fieldList;
    }

    @Override
    public boolean update(Field field) throws ParseException, SQLException {
        Connection conn = controller.getConnection();
        String query = "UPDATE field SET fieldName = ?, fieldStatus =? WHERE fieldLabel = ? AND uid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, field.getFieldName());
        preparedStatement.setString(2, field.getFieldStatus().name());

        preparedStatement.setString(3, field.getFieldLabel());
        preparedStatement.setInt(4, field.getUid());

        return  preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        Connection conn = controller.getConnection();
        String query = "DELETE FROM field WHERE uid = ? AND fieldLabel= ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, label);


        return  preparedStatement.executeUpdate() == 1;
    }


    public Double getUsedFieldSize(int id) throws SQLException {
        Connection conn = controller.getConnection();
        String query = "SELECT SUM(fieldSize) FROM field WHERE uid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        Double fieldSize = null;
        while (resultSet.next()){
            String size= resultSet.getString(1);
            if(size!=null) fieldSize = new Double(size);
        }
        return fieldSize;
    }

    public int getFieldId (String fieldName, int id) throws SQLException {
        Connection conn = controller.getConnection();
        String query = "SELECT id FROM field WHERE fieldName = ? AND uid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, fieldName);
        preparedStatement.setInt(2, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        int fieldId = 0;
        while (resultSet.next()){
            String fId =  resultSet.getString(1);
            if(fId!=null) fieldId= Integer.parseInt(fId);
        }
        return fieldId;
    }
}
