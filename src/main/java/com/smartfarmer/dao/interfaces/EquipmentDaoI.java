package com.smartfarmer.dao.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface EquipmentDaoI <Equipment> {
    boolean add(Equipment equipment) throws ParseException, SQLException;
    List<Equipment> read(int id) throws SQLException, ParseException;
    boolean update(Equipment equipment) throws ParseException, SQLException;
    boolean delete(String label, int id) throws ParseException, SQLException;
}
