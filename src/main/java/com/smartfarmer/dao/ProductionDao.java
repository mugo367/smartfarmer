package com.smartfarmer.dao;


import com.smartfarmer.dao.interfaces.ProductionDaoI;
import com.smartfarmer.model.Production;
import com.smartfarmer.model.enumFiles.Unit;
import com.smartfarmer.util.EntityManager;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Named(value ="ProductionDao")
public class ProductionDao implements ProductionDaoI<Production> {
    @Inject
    EntityManager entityManager;
    @Override
    public boolean add(Production production) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();

        String query = "INSERT INTO productions (productionLabel, productionDate, fieldId, productionQuantity, unit, productionDetails, uid)  " +
                "values (?, ?,?, ?, ?,?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, production.getProductionLabel());
        preparedStatement.setDate(2, new java.sql.Date(production.getProductionDate().getTime()));
        preparedStatement.setInt(3, production.getFieldId());
        preparedStatement.setDouble(4, production.getProductionQuantity());
        preparedStatement.setString(5, production.getUnit().name());
        preparedStatement.setString(6, production.getProductionDetails());
        preparedStatement.setInt(7,production.getUid());

        return  preparedStatement.executeUpdate() == 1;
    }

    @Override
    public List<Production> read(int id) throws SQLException, ParseException {
        List<Production> productionList = new ArrayList<>();
        Connection conn = entityManager.getConnection();
        String query = "SELECT fieldName, productionLabel, productionDate, productionQuantity, unit, productionDetails FROM productions INNER JOIN field on productions.fieldId = field.id WHERE productions.uid = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Production production = new Production();
            production.setFieldName(resultSet.getString(1));
            production.setProductionLabel(resultSet.getString(2));
            production.setProductionDate(new java.util.Date(resultSet.getDate(3).getTime()));
            production.setProductionQuantity(resultSet.getDouble(4));
            production.setUnit(Unit.valueOf(resultSet.getString(5)));
            production.setProductionDetails(resultSet.getString(6));
            productionList.add(production);
        }
        return productionList;
    }

    @Override
    public boolean update(Production production) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();
        String query ="UPDATE productions SET productionQuantity = ?, unit = ?, productionDetails = ? " +
                "WHERE uid = ? AND productionLabel = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setDouble(1, production.getProductionQuantity());
        preparedStatement.setString(2, production.getUnit().name());
        preparedStatement.setString(3, production.getProductionDetails());
        preparedStatement.setInt(4, production.getUid());
        preparedStatement.setString(5, production.getProductionLabel());

        return  preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        Connection conn = entityManager.getConnection();
        String query = "DELETE FROM productions WHERE uid =? AND productionLabel = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, label);

        return  preparedStatement.executeUpdate() == 1;
    }


}
