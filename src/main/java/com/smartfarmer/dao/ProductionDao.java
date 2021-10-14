package com.smartfarmer.dao;


import com.smartfarmer.model.Production;
import com.smartfarmer.model.enumFiles.Unit;
import com.smartfarmer.util.Controller;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Named(value ="ProductionDao")
public class ProductionDao implements DaoI<Production> {
    @Inject
    Controller controller;
    @Override
    public boolean add(Production production) throws ParseException, SQLException {
        int fieldId = new FieldDetailDao().getFieldId(production.getFieldName(), production.getUid());
        String query = "INSERT INTO productions (productionLabel, productionDate, fieldId, productionQuantity, unit, productionDetails, uid)  values (" +
                "'"+production.getProductionLabel()+"', '"+new java.sql.Date(production.getProductionDate().getTime())+"', "+fieldId+", '"+production.getProductionQuantity()+"', '"+production.getUnit()+"', '"+production.getProductionDetails()+"', "+production.getUid()+")";

        return controller.writeData(query) == 1;
    }

    @Override
    public List<Production> read(int id) throws SQLException, ParseException {
        List<Production> productionList = new ArrayList<>();
        String query = "SELECT fieldName, productionLabel, productionDate, productionQuantity, unit, productionDetails FROM productions INNER JOIN field on productions.fieldId = field.id WHERE productions.uid = "+id+"";
        ResultSet resultSet = controller.readData(query);
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
        String query ="UPDATE productions SET productionQuantity = '"+production.getProductionQuantity()+"', unit ='"+production.getUnit()+"', productionDetails = '"+production.getProductionDetails()+"' " +
                "WHERE uid = "+production.getUid()+" AND productionLabel = '"+production.getProductionLabel()+"'";
        return controller.writeData(query)==1;
    }

    @Override
    public boolean delete(String label, int id) throws ParseException, SQLException {
        String query = "DELETE FROM productions WHERE uid = "+id+" AND productionLabel = '"+label+"'";
        return controller.writeData(query)==1;
    }


}
