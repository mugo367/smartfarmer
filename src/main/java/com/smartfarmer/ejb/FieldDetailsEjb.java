package com.smartfarmer.ejb;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.dao.interfaces.FieldDetailDaoI;
import com.smartfarmer.ejb.interfaces.FieldDetailEjbI;
import com.smartfarmer.entities.Field;
import com.smartfarmer.entities.enumFiles.FieldStatus;
import com.smartfarmer.util.AppException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class FieldDetailsEjb implements FieldDetailEjbI {
    @Inject
    FieldDetailDaoI fieldDetailDao;

    @Override
    public Field addField(Field field) throws Exception {

        if (field == null)
            throw new AppException("Invalid field details!!");
        /**
         * CHECK LOGIC
         * get used field size // remaining land
         * */

        if(field.getFieldStatus() == null & field.getFieldStatusStr() != null){
            field.setFieldStatus(FieldStatus.valueOf(field.getFieldStatusStr()));
        }

        return fieldDetailDao.save(field);
    }

    @Override
    public Field editField(Field field) {
        return fieldDetailDao.edit(field);
    }

    @Override
    public ModelListWrapper<Field> listFields(Field filter, int start, int limit) {
        return fieldDetailDao.list(filter, start, limit);
    }

    @Override
    public void deleteField(Long id) {
        fieldDetailDao.deleteById(id);
    }

    @Override
    public Optional<Field> findById(Long id) {
        return fieldDetailDao.findById(id);
    }
}
