package com.smartfarmer.ejb;

import com.smartfarmer.dao.interfaces.EquipmentDaoI;
import com.smartfarmer.ejb.interfaces.EquipmentEjbI;
import com.smartfarmer.entities.AuditTrail;
import com.smartfarmer.entities.Equipment;
import com.smartfarmer.entities.enumFiles.Condition;
import com.smartfarmer.model.Response;
import com.smartfarmer.util.AppException;
import com.smartfarmer.util.ModelListWrapper;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;

@Stateless
public class EquipmentEJB implements EquipmentEjbI {
    @Inject
    EquipmentDaoI equipmentDao;

    @Inject
    private Event<AuditTrail> auditTrailEvent;

    @Override
    public Response add(Equipment equipment) throws Exception {

        if (equipment == null)
            throw new AppException("Invalid equipment details!!");

        if(equipment.getEquipmentCondition() == null && equipment.getEquipmentConditionStr() !=null)
            equipment.setEquipmentCondition(Condition.valueOf(equipment.getEquipmentConditionStr()));

        return new Response(true, "Successfully added",  equipmentDao.save(equipment));
    }

    @Override
    public Equipment edit(Equipment equipment) {
        return equipmentDao.edit(equipment);
    }

    @Override
    public ModelListWrapper<Equipment> list(Equipment filter, int start, int limit) {
        return equipmentDao.list(filter, start, limit);
    }

    @Override
    public void delete(Long id) {
        equipmentDao.deleteById(id);
        auditTrailEvent.fire(new AuditTrail("User deleted equipment " , new Date()));
    }

    @Override
    public Optional<Equipment> findById(Long id) {
        return equipmentDao.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return equipmentDao.existsById(id);
    }
}
