package com.home.SpringBootAutomation.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "bankEntity")
@Table(name = "bank_tbl")

public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bank_openingDate")            //      تاریخ افتتاح حساب
    private LocalDateTime accountOpeningDate;

    @Column(name = "bank_accountNumber")          //      شماره حساب
    private String accountNumber;

    @Column(name = "bank_accountOwner")           //      صاحب حساب
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private String accountOwner;

    @Column(name = "bank_accountNumber")          //      بانک و شعبه
    private String bankAndBranch;

    @Column(name = "bank_accountNumber")          //      نوع حساب
    private String accountType;

    @Column(name = "bank_AccountStatus")          //      وضعیت حساب
    private String AccountStatus;

    @Column(name = "bank_Balance")                //      موجودی حساب
    private int Balance;
}