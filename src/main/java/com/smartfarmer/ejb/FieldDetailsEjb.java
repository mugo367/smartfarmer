package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.FarmerDaoI;
import com.smartfarmer.dao.interfaces.FieldDetailDaoI;
import com.smartfarmer.ejb.interfaces.FieldDetailEjbI;
import com.smartfarmer.entities.Field;
import com.smartfarmer.entities.enumFiles.FieldStatus;
import com.smartfarmer.model.Response;
import com.smartfarmer.util.AppException;
import com.smartfarmer.util.ModelListWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class FieldDetailsEjb implements FieldDetailEjbI {
    @Inject
    FieldDetailDaoI fieldDetailDao;

    @Inject
    FarmerDaoI farmerDao;

    @Override
    public Response add(Field field) throws Exception {

        if (field == null)
            throw new AppException("Invalid field details!!");

        double remainingLand = farmerDao.getTotalFarmSize() - fieldDetailDao.getUsedFieldSize();

        System.out.println(remainingLand);

        if(field.getFieldStatus() == null & field.getFieldStatusStr() != null){
            field.setFieldStatus(FieldStatus.valueOf(field.getFieldStatusStr()));
        }

        if(field.getFieldSize() > remainingLand)
            return new Response(false, "Entered field size is greater than remaining " +
                    "Remaining land size is "+ remainingLand,  field);
        else
            return new Response(true, "Successfully added",  fieldDetailDao.save(field));
    }

    @Override
    public Field edit(Field field) {
        return fieldDetailDao.edit(field);
    }

    @Override
    public ModelListWrapper<Field> list(Field filter, int start, int limit) {
        return fieldDetailDao.list(filter, start, limit);
    }

    @Override
    public void delete(Long id) {
        fieldDetailDao.deleteById(id);
    }

    @Override
    public Optional<Field> findById(Long id) {
        return fieldDetailDao.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return fieldDetailDao.existsById(id);
    }
}
