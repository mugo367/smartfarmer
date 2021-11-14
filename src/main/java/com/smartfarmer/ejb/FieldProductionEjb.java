package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.FieldDetailDaoI;
import com.smartfarmer.dao.interfaces.FieldProductionDaoI;
import com.smartfarmer.ejb.interfaces.FieldProductionEjbI;
import com.smartfarmer.entities.AuditTrail;
import com.smartfarmer.entities.Field;
import com.smartfarmer.entities.FieldProduction;
import com.smartfarmer.model.Response;
import com.smartfarmer.util.ModelListWrapper;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Optional;

@Stateless
public class FieldProductionEjb implements FieldProductionEjbI {

   @PersistenceContext
   private EntityManager entityManager;

   @Inject
   private FieldProductionDaoI fieldProductionDao;

   @Inject
   private FieldDetailDaoI fieldDetailDao;

   @Inject
   private Event<AuditTrail> auditTrailEvent;

   @Override
   public Response add(FieldProduction fieldProduction) throws Exception {

      if (fieldProduction.getFieldId() > 0)
         fieldProduction.setField(entityManager.find(Field.class, fieldProduction.getFieldId()));

      fieldDetailDao.updateFieldStatus(fieldProduction.getFieldId());

      return new Response(true, "Successfully added",  fieldProductionDao.save(fieldProduction));
   }

   @Override
   public FieldProduction edit(FieldProduction fieldProduction) {
      return  fieldProductionDao.edit(fieldProduction);
   }

   @Override
   public ModelListWrapper<FieldProduction> list(FieldProduction filter, int start, int limit) {
      return fieldProductionDao.list(filter, start, limit);
   }

   @Override
   public void delete(Long id) {
      fieldProductionDao.deleteById(id);
      auditTrailEvent.fire(new AuditTrail("User deleted production " , new Date()));
   }

   @Override
   public Optional<FieldProduction> findById(Long id) {
      return fieldProductionDao.findById(id);
   }


   @Override
   public boolean existsById(Long id) {
      return fieldProductionDao.existsById(id);
   }
}
