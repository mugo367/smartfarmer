package com.smartfarmer.ejb;

import com.google.gson.Gson;
import com.smartfarmer.dao.interfaces.ProductionDaoI;
import com.smartfarmer.ejb.interfaces.ProductionEjbI;
import com.smartfarmer.model.Production;
import com.smartfarmer.model.ResultWrapper;
import com.smartfarmer.model.enumFiles.Unit;
import com.smartfarmer.util.BeanUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class ProductionEjb implements ProductionEjbI {
    @Inject
    ProductionDaoI<Production> productionDao;
    @Override
    public ResultWrapper addProduction(Map<String, String[]> formData, int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        Production production = new Production();
        try {
            BeanUtils.populate(production, formData);
            if(production.getUnit() == null && production.getUnitStr() !=null )
                production.setUnit(Unit.valueOf(production.getUnitStr()));

            production.setUid(id);
        } catch (Exception ex) {
            resultWrapper.setMessage(ex.getMessage());
            ex.printStackTrace();
            production = null;
        }
        if (production == null){
            resultWrapper.setSuccess(false);
            return resultWrapper;
        }
        try {
            productionDao.add(production);
        } catch (ParseException | SQLException e) {
            resultWrapper.setSuccess(false);
            resultWrapper.setMessage(e.getMessage());
        }
        return  resultWrapper;
    }

    @Override
    public ResultWrapper listProductions(int id) {
        ResultWrapper resultWrapper = new ResultWrapper();
        List<Production> productionList = new ArrayList<>();

        try {
            productionList = productionDao.read(id);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        resultWrapper.setList(productionList);
        return resultWrapper;
    }

    @Override
    public void deleteProduction(String productions, int id) {
        try {
            List<String> productionLabels = new Gson().fromJson( productions, List.class );

            for(String productionLabel : productionLabels) {
                productionDao.delete(productionLabel, id);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
