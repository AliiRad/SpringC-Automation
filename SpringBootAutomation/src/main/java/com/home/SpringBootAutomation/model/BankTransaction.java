package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "BankTransactionEntity")
@Table(name = "BankTransaction_tbl")
public class BankTransaction {
    @Id
    @SequenceGenerator(name = "bankTransactionSeq", sequenceName = "bankTransaction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankTransactionSeq")
    @Column(name = "bankTransaction_id",length = 20)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "bankTransaction_balance")
    private FinancialDocument balance;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "bankTransaction_financialDocument")
    private FinancialDocument financialDocument;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "bankTransaction_employee")
    private Person employee;

    @Column(name = "bankTransaction_deleted")
    private Boolean deleted;
}