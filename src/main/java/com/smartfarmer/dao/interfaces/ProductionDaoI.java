package com.smartfarmer.dao.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ProductionDaoI<Production> {
    boolean add(Production t) throws ParseException, SQLException;
    List<Production> read(int id) throws SQLException, ParseException;
    boolean update(Production production) throws ParseException, SQLException;
    boolean delete(String label, int id) throws ParseException, SQLException;
}

