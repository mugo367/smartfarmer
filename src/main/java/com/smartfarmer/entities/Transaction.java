package com.smartfarmer.model;

import com.smartfarmer.model.enumFiles.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "tbl_transactions")
public class Transaction extends BaseEntity  implements Serializable {

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

    @Transient
    private String transactionTypeStr;

}
