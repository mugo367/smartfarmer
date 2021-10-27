package com.smartfarmer.dao.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ActivityDaoI<Activity> {
    boolean add(Activity activity) throws ParseException, SQLException;
    List<Activity> read(int id) throws SQLException, ParseException;
    boolean update(Activity activity) throws ParseException, SQLException;
    boolean delete(String label, int id) throws ParseException, SQLException;
}