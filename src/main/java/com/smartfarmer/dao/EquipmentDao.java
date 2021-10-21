package com.smartfarmer.dao;

import com.smartfarmer.model.Equipment;
import com.smartfarmer.model.enumFiles.Condition;
import com.smartfarmer.util.Controller;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Connection conn = controller.getConnection();
        String query = "INSERT INTO equipments (equipmentLabel, equipmentName, equipmentCondition, equipmentQuantity, uid) " +
                "VALUES (?, ?, ?, ?, ? )";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, equipment.getEquipmentLabel());
        preparedStatement.setString(2, equipment.getEquipmentName());
        preparedStatement.setString(3, equipment.getEquipmentCondition().name());
        preparedStatement.setString(4, equipment.getEquipmentQuantity());
        preparedStatement.setInt(5, equipment.getUid());

        return  preparedStatement.executeUpdate() == 1;
    }

    @Override
    public List<Equipment> read(int id) throws SQLException, ParseException {
        Connection conn = controller.getConnection();
        List<Equipment> equipmentList = new ArrayList<>();

        String query = "SELECT * FROM equipments WHERE uid =?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Equipment equipment = new Equipment();
            equipment.setId(resultSet.getInt(1));
            equipment.setEquipmentLabel(resultSet.getString(2));
            equipment.setEquipmentName(resultSet.getString(3));
            equipment.setEquipmentCondition(Condition.valueOf(resultSet.getString(4)));
            equipment.setEquipmentQuantity(resultSet.getString(5));
            equipment.setUid(resultSet.getInt(6));
            equipmentList.add(equipment);
        }
        return equipmentList;
    }

    @Override
    public boolean update(Equipment equipment) throws ParseException, SQLException {
        Connection conn = controller.getConnection();

        String query = "UPDATE equipments SET equipmentName = ?, equipmentCondition = ?, equipmentQuantity = ? WHERE equipmentLabel =? AND uid =";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, equipment.getEquipmentName());
        preparedStatement.setString(2, equipment.getEquipmentCondition().name());
        preparedStatement.setString(3, equipment.getEquipmentQuantity());
        preparedStatement.setString(4, equipment.getEquipmentLabel());
        preparedStatement.setInt(5, equipment.getUid());

        return preparedStatement.executeUpdate()==1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        Connection conn = controller.getConnection();

        String query = "DELETE FROM equipments WHERE equipmentLabel =? AND uid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, label);
        preparedStatement.setInt(2, id);
        return preparedStatement.executeUpdate() ==1;
    }

}
