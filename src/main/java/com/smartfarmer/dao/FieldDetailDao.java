package com.smartfarmer.dao;


import com.smartfarmer.model.Field;
import com.smartfarmer.model.enumFiles.FieldStatus;
import com.smartfarmer.util.Controller;

import javax.inject.Inject;
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

        String query = "INSERT INTO field(fieldLabel, fieldName, fieldSize, fieldStatus, uid) " +
                "VALUES('"+field.getFieldLabel()+"', '"+field.getFieldName()+"', "+field.getFieldSize()+", '"+field.getFieldStatus()+"' , "+field.getUid()+" )";

        return controller.writeData(query)==1;
    }

    @Override
    public List<Field> read(int id) throws SQLException, ParseException {
        List<Field> fieldList = new ArrayList<>();
        String query = "SELECT * FROM field WHERE uid ="+id+"" ;
        ResultSet resultSet = controller.readData(query);
        while (resultSet.next()){
           Field field = new Field();
           field.setFieldLabel(resultSet.getString(2));
           field.setFieldName(resultSet.getString(3));
           field.setFieldSize(resultSet.getDouble(4));
           field.setFieldStatus(FieldStatus.valueOf(resultSet.getString(5)));
           fieldList.add(field);
        }
        return fieldList;
    }

    @Override
    public boolean update(Field field) throws ParseException, SQLException {
        String query = "UPDATE field SET fieldName = '"+field.getFieldName()+"', fieldStatus ='"+field.getFieldStatus()+"' WHERE fieldLabel = '"+field.getFieldLabel()+"' AND uid ="+field.getUid()+"";
        return controller.writeData(query)==1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        String query = "DELETE FROM field WHERE uid = "+id+" AND fieldLabel='"+label+"'";
        return controller.writeData(query)==1;
    }


    public Double getUsedFieldSize(int id) throws SQLException {
        String query = "SELECT SUM(fieldSize) FROM field WHERE uid ="+id+"";
        ResultSet resultSet = controller.readData(query);
        Double fieldSize = null;
        while (resultSet.next()){
            String size= resultSet.getString(1);
            if(size!=null) fieldSize = new Double(size);
        }
        return fieldSize;
    }

    public int getFieldId (String fieldName, int id) throws SQLException {
        String query = "SELECT id FROM field WHERE fieldName = "+fieldName+" AND uid = "+id+"";
        ResultSet resultSet = controller.readData(query);
        int fieldId = 0;
        while (resultSet.next()){
            String fId =  resultSet.getString(1);
            if(fId!=null) fieldId= Integer.parseInt(fId);
        }
        return fieldId;
    }
}
