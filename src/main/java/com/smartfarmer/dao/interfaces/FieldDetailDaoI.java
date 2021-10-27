package com.smartfarmer.dao.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface FieldDetailDaoI<Field> {

    boolean add(Field field) throws ParseException, SQLException;
    List<Field> read(int id) throws SQLException, ParseException;
    boolean update(Field field) throws ParseException, SQLException;
    boolean delete(String label, int id) throws ParseException, SQLException;
    Double getUsedFieldSize(int id) throws SQLException;
    int getFieldId (String fieldName, int id) throws SQLException;
}
