package com.smartfarmer.dao.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface DaoI<T> {
    boolean add(T t) throws ParseException, SQLException;
    List<T> read(int id) throws SQLException, ParseException;
    boolean update(T t) throws ParseException, SQLException;
    boolean delete(String label, int id) throws ParseException, SQLException;
}
