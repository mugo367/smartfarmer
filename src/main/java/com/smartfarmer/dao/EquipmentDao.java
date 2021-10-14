package com.smartfarmer.dao;

import com.smartfarmer.model.Equipment;
import com.smartfarmer.model.enumFiles.Condition;
import com.smartfarmer.util.Controller;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Named(value ="EquipmentDao")
public class EquipmentDao implements DaoI<Equipment> {
    @Inject
    Controller controller;
    @Override
    public boolean add(Equipment equipment) throws ParseException, SQLException {
        String query = "INSERT INTO equipments (equipmentLabel, equipmentName, equipmentCondition, equipmentQuantity, uid) " +
                "VALUES ('"+equipment.getEquipmentLabel()+"', '"+equipment.getEquipmentName()+"','"+equipment.getEquipmentCondition()+"','"+equipment.getEquipmentQuantity()+"', "+equipment.getUid()+")";

        return (controller.writeData(query)==1);
    }

    @Override
    public List<Equipment> read(int id) throws SQLException, ParseException {
        List<Equipment> equipmentList = new ArrayList<>();

        String query = "SELECT * FROM equipments WHERE uid ="+id+"";
        ResultSet resultSet = controller.readData(query);
        while (resultSet.next()){
            Equipment equipment = new Equipment();
            equipment.setEquipmentLabel(resultSet.getString(2));
            equipment.setEquipmentName(resultSet.getString(3));
            equipment.setEquipmentCondition(Condition.valueOf(resultSet.getString(4)));
            equipment.setEquipmentQuantity(resultSet.getString(5));
            equipmentList.add(equipment);
        }
        return equipmentList;
    }

    @Override
    public boolean update(Equipment equipment) throws ParseException, SQLException {
        String query = "UPDATE equipments SET equipmentName = '"+equipment.getEquipmentName()+"', equipmentCondition = '"+equipment.getEquipmentCondition()+"'," +
                "equipmentQuantity = "+equipment.getEquipmentQuantity()+"";
        return controller.writeData(query)==1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        String query = "DELETE FROM equipments WHERE equipmentLabel ='"+label+"', uid="+id+"";
        return controller.writeData(query)==1;
    }

}
