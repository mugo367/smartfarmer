package com.smartfarmer.events;

import com.smartfarmer.entities.AuditTrail;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuditTrailListener {
   @PersistenceContext
   private EntityManager em;

   @Asynchronous
   public void saveAuditTrail(@Observes AuditTrail auditTrail){
      em.merge(auditTrail);
   }

}
