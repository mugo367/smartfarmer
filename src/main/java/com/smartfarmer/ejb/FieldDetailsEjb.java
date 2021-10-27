package com.smartfarmer.ejb;

import com.google.gson.Gson;
import com.smartfarmer.dao.interfaces.FieldDetailDaoI;
import com.smartfarmer.ejb.interfaces.FieldDetailEjbI;
import com.smartfarmer.model.Farmer;
import com.smartfarmer.model.Field;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.model.enumFiles.FieldStatus;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class FieldDetailsEjb implements FieldDetailEjbI {
    @Inject
    FieldDetailDaoI<Field> fieldDetailDao;
    @Override
    public ResultWrapper addField(Map<String, String[]> formData, int id, Farmer farmerDetails) {
        ResultWrapper resultWrapper = new ResultWrapper();
        Field field = new Field();
        try {
            Double totalFieldSize = farmerDetails.getFarmSize();
            Double usedField = fieldDetailDao.getUsedFieldSize(id);
            double remainingFieldSize = totalFieldSize - usedField;
            BeanUtils.populate(field, formData);
            field.setUid(id);

            if(field.getFieldStatus() == null & field.getFieldStatusStr() != null){
                field.setFieldStatus(FieldStatus.valueOf(field.getFieldStatusStr()));
            }


            if(remainingFieldSize < field.getFieldSize()){
                resultWrapper.setSuccess(false);
                resultWrapper.setMessage("Field Size entered is greater than the remaining size");
                return resultWrapper;
            }

        } catch (Exception e) {
            resultWrapper.setMessage(e.getMessage());
            field = null;
        }

        if (field == null){
            resultWrapper.setSuccess(false);
            return resultWrapper;
        }

        try {
            fieldDetailDao.add(field);
        } catch (ParseException | SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        return resultWrapper;
    }

    @Override
    public ResultWrapper listFields(int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        List<Field> fieldList = new ArrayList<>();

        try {
            fieldList = fieldDetailDao.read(id);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        resultWrapper.setList(fieldList);
        return resultWrapper;
    }

    @Override
    public void deleteField(String field, int id) {
        try {
            List<String> fieldLabels = new Gson().fromJson( field, List.class );

            for(String fieldLabel : fieldLabels) {
                fieldDetailDao.delete(fieldLabel, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
