package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.DocumentType;
import com.home.SpringBootAutomation.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "FinancialDocumentEntity")
@Table(name = "FinancialDocument_tbl")
public class FinancialDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "financialDocument_id",length = 20)
    private Long id;

    @Column(name = "financialDocument_documentDate")            //      تاریخ سند
    private LocalDate documentDate;

    @Column(name = "financialDocument_amount")                  //      مبلغ تراکنش
    private int Amount;

    @Column(name = "financialDocument_behalf")                  //      بابت
    private String behalf;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name = "financialDocument_account")                 //      حساب
    private Account account;

    @Column(name = "financialDocument_transactionType")         //      نوع تراکنش
    private TransactionType transactionType;

    @Column(name = "financialDocument_documentType")            //      نوع سند
    private DocumentType documentType;

    @Column(name = "financialDocument_customer")                //      مشتری
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private String customer;
}