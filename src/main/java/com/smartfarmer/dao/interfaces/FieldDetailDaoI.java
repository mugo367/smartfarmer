package com.smartfarmer.dao.interfaces;

import com.smartfarmer.entities.Field;

public interface FieldDetailDaoI extends DaoI<Field, Long> {

    Double getUsedFieldSize(int id);
}
