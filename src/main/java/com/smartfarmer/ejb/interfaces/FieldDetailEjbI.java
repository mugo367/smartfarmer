package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.entities.Field;

import java.util.Optional;

public interface FieldDetailEjbI {
    Field addField(Field field) throws Exception;
    Field editField(Field field);
    ModelListWrapper<Field> listFields(Field filter, int start, int limit);
    void deleteField(Long id);
    Optional<Field> findById(Long id);
}
