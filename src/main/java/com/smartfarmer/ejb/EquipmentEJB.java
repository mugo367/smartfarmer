package com.smartfarmer.ejb;


import com.google.gson.Gson;
import com.smartfarmer.dao.interfaces.EquipmentDaoI;
import com.smartfarmer.ejb.interfaces.EquipmentEjbI;
import com.smartfarmer.entities.Equipment;
import com.smartfarmer.entities.enumFiles.Condition;
import com.smartfarmer.model.ResultWrapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class EquipmentEJB implements EquipmentEjbI {
    @Inject
    EquipmentDaoI<Equipment> equipmentDao;

    @Override
    public ResultWrapper addEquipment(Map<String, String[]> formData, int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        Equipment equipment = new Equipment();
        try {
            BeanUtils.populate(equipment, formData);
            if(equipment.getEquipmentCondition() == null && equipment.getEquipmentConditionStr() !=null)
                equipment.setEquipmentCondition(Condition.valueOf(equipment.getEquipmentConditionStr()));

            equipment.setUid(id);
        } catch (Exception ex) {
            resultWrapper.setMessage(ex.getMessage());
            ex.printStackTrace();
            equipment = null;
        }

        if (equipment == null){
            resultWrapper.setSuccess(false);
            return resultWrapper;
        }

        try {
            equipmentDao.add(equipment);
        } catch (ParseException | SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        return resultWrapper;
    }

    @Override
    public ResultWrapper listEquipments(int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        List<Equipment> equipmentList = new ArrayList<>();

        try {
            equipmentList = equipmentDao.read(id);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        resultWrapper.setList(equipmentList);
        return resultWrapper;
    }

    @Override
    public void deleteEquipments(String equipment, int id) {
        try {
            List<String> equipmentLabels = new Gson().fromJson( equipment, List.class );

            for(String equipmentLabel : equipmentLabels) {
                equipmentDao.delete(equipmentLabel, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
