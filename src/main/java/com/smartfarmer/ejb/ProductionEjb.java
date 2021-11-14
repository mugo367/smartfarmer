package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.FieldDetailDaoI;
import com.smartfarmer.dao.interfaces.ProductionDaoI;
import com.smartfarmer.ejb.interfaces.ProductionEjbI;
import com.smartfarmer.entities.AuditTrail;
import com.smartfarmer.entities.Field;
import com.smartfarmer.entities.Production;
import com.smartfarmer.entities.enumFiles.Unit;
import com.smartfarmer.model.Response;
import com.smartfarmer.util.AppException;
import com.smartfarmer.util.ModelListWrapper;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Optional;

@Stateless
public class ProductionEjb implements ProductionEjbI {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    ProductionDaoI productionDao;

    @Inject
    private FieldDetailDaoI fieldDetailDao;

    @Inject
    private Event<AuditTrail> auditTrailEvent;


    @Override
    public Response add(Production production) throws Exception {

        if (production == null)
            throw new AppException("Invalid production details!!");

        if(production.getUnit() == null && production.getUnitStr() !=null )
            production.setUnit(Unit.valueOf(production.getUnitStr()));

        if (production.getFieldId() > 0)
            production.setField(entityManager.find(Field.class, production.getFieldId()));

        if(fieldDetailDao.checkFieldStatus(production.getFieldId()) !=null)
            return new Response(true, "Successfully added",  productionDao.save(production));
        else
            return new Response(false, "Field is not under Production ",  production);
    }

    @Override
    public Production edit(Production production) {
        return productionDao.edit(production);
    }

    @Override
    public ModelListWrapper<Production> list(Production filter, int start, int limit) {
        return productionDao.list(filter, start, limit);
    }

    @Override
    public void delete(Long id) {
        productionDao.deleteById(id);
        auditTrailEvent.fire(new AuditTrail("User deleted Harvest " , new Date()));
    }

    @Override
    public Optional<Production> findById(Long id) {
        return productionDao.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return productionDao.existsById(id);
    }
}
