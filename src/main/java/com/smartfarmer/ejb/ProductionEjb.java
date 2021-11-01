package com.smartfarmer.ejb;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.dao.interfaces.ProductionDaoI;
import com.smartfarmer.ejb.interfaces.ProductionEjbI;
import com.smartfarmer.entities.Field;
import com.smartfarmer.entities.Production;
import com.smartfarmer.entities.enumFiles.Unit;
import com.smartfarmer.util.AppException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

@Stateless
public class ProductionEjb implements ProductionEjbI {
    @Inject
    private EntityManager entityManager;

    @Inject
    ProductionDaoI productionDao;


    @Override
    public Production addProduction(Production production) throws Exception {

        if (production == null)
            throw new AppException("Invalid production details!!");

        if(production.getUnit() == null && production.getUnitStr() !=null )
            production.setUnit(Unit.valueOf(production.getUnitStr()));

        if (production.getFieldId() > 0)
            production.setField(entityManager.find(Field.class, production.getFieldId()));

        return productionDao.save(production);
    }

    @Override
    public Production editProduction(Production production) {
        return productionDao.edit(production);
    }

    @Override
    public ModelListWrapper<Production> listProductions(Production filter, int start, int limit) {
        return productionDao.list(filter, start, limit);
    }

    @Override
    public void deleteProduction(Long id) {
        productionDao.deleteById(id);
    }

    @Override
    public Optional<Production> findById(Long id) {
        return productionDao.findById(id);
    }
}
