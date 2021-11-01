package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.entities.Equipment;

import java.util.Optional;

public interface EquipmentEjbI {
    Equipment addEquipment(Equipment equipment) throws Exception;
    Equipment editEquipment(Equipment equipment);
    ModelListWrapper<Equipment> listEquipments(Equipment filter, int start, int limit);
    void deleteEquipment(Long id);
    Optional<Equipment> findById(Long id);
}
