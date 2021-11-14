package com.smartfarmer.dao.interfaces;

import com.smartfarmer.entities.Field;

public interface FieldDetailDaoI extends DaoI<Field, Long> {

    double getUsedFieldSize();
    void updateFieldStatus(long id);
    Field checkFieldStatus(long id);
}
