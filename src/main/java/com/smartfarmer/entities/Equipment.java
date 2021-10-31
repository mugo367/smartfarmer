package com.smartfarmer.entities;


import com.smartfarmer.entities.enumFiles.Condition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tbl_equipments")
public class Equipment extends BaseEntity {

    @Column (name="equipment_label", unique = true)
    private String equipmentLabel;

    @Column (name="equipment_name")
    private String equipmentName;

    @Column (name="equip_condition")
    @Enumerated(EnumType.STRING)
    private Condition equipmentCondition;

    @Column (name="quantity")
    private String equipmentQuantity;

    @Transient
    private String equipmentConditionStr;

}
