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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bankTransaction_id",length = 20)
    private Long id;

    //todo: add balance from financialDocument
    @Column(name = "bankTransaction_Balance")                  //     موجودی
    private int balance;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name = "bankTransaction_FinancialDocument")       //      شماره سند مالی
    private FinancialDocument financialDocument;

    @Column(name = "bankTransaction_employee")                //      کارمند
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private String employee;

}
