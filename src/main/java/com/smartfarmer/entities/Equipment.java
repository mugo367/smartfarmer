package com.smartfarmer.model;


import com.smartfarmer.model.enumFiles.Condition;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tbl_equipments")
public class Equipment extends BaseEntity implements Serializable {

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
