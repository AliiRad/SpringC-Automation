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

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "bank_transaction_balance")
    private FinancialDocument balance;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "bank_transaction_financial_document")
    private FinancialDocument financialDocument;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "bank_transaction_employee")
    private Person employee;

    @Column(name = "bank_transaction_deleted")
    private boolean deleted;
}