package com.smartfarmer.model;

import com.smartfarmer.model.enumFiles.FieldStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tbl_fields")
public class Field  extends BaseEntity implements Serializable {

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
