package com.smartfarmer.entities;

import com.smartfarmer.entities.enumFiles.FieldStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tbl_fields")
public class Field  extends BaseEntity {

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY)
    private List<Production> productions = new ArrayList<Production>();

    @Column(name = "field_label", unique = true)
    private String fieldLabel;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_size")
    private Double fieldSize;

    @Column(name = "field_status")
    @Enumerated(EnumType.STRING)
    private FieldStatus fieldStatus;

    @Transient
    private String fieldStatusStr;



}
