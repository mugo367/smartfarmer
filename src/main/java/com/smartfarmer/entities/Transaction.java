package com.smartfarmer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartfarmer.entities.enumFiles.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "tbl_transactions")
public class Transaction extends BaseEntity{

    @Column(name = "transaction_date")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_label", unique = true)
    private String transactionLabel;

    @Column(name = "costPerUnit")
    private double costPerUnit;

    @Column
    private int units;

    @Column(name = "transaction_cost")
    private double transactionCost;

    @Column(name = "details", columnDefinition = "LONGTEXT")
    private String transactionDetails;

    @Getter(onMethod_ = @JsonIgnore)
    @Transient
    private String transactionTypeStr;

}
