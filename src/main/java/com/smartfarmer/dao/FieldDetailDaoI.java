package com.smartfarmer.dao;

import com.smartfarmer.model.Field;

import java.sql.SQLException;

public interface FieldDetailDaoI extends DaoI<Field> {

    Double getUsedFieldSize(int id) throws SQLException;

    int getFieldId (String fieldName, int id) throws SQLException;
}
