package com.smartfarmer.entities;

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
@Table(name="tbl_started_productions")
public class FieldProduction extends BaseEntity{

   @ManyToOne(optional = false)
   private Field field;

   @Formula("(field_id)")
   private long fieldId;

   @Formula("(select f.field_name from tbl_fields f where f.id=field_id)")
   private String fieldName;

   @Temporal(TemporalType.DATE)
   private Date startDate;

   @Column(name ="crop_planted")
   private Date endDate;

   @Column(name="description", columnDefinition = "LONGTEXT")
   private String description;
}
