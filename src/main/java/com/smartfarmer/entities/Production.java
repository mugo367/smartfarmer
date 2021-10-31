package com.smartfarmer.entities;

import com.smartfarmer.entities.enumFiles.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="tbl_productions")
public class Production extends BaseEntity {

    @Column(name = "production_label", unique = true)
    private String productionLabel;

    @Column(name = "production_date")
    @Temporal(TemporalType.DATE)
    private Date productionDate;

    @ManyToOne
    private Field field;

    @Formula("(field_id)")
    private int fieldId;

    @Formula("(select f.field_name from tbl_fields f where f.id=field_id and f.uid=uid)")
    private String fieldName;

    @Column(name = "quantity")
    private Double productionQuantity;

    @Column
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Column(name = "details", columnDefinition = "LONGTEXT")
    private String productionDetails;

    @Transient
    private String unitStr;
}
