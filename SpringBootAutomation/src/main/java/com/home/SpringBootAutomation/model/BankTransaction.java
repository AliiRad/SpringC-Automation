package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "bankTransactionEntity")
@Table(name = "bank_transaction_tbl")

public class BankTransaction {
    @Id
    @SequenceGenerator(name = "bankTransactionSeq", sequenceName = "bankTransaction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankTransactionSeq")
    @Column(name = "bank_transaction_id",length = 20)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_transaction_balance")
    private FinancialDocument balance;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_transaction_f_d")
    private FinancialDocument financialDocument;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_transaction_person")
    private Person person;

    @Column(name = "bank_transaction_deleted")
    private boolean deleted;

    public Long getFinancialDocumentId() {
        return null;
    }
}