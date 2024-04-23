package com.home.SpringBootAutomation.model;

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

@Entity(name = "accountEntity")
@Table(name = "account_tbl")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id",length = 20)
    private Long id;

    @Column(name = "account_openingDate")            //      تاریخ افتتاح حساب
    private LocalDate accountOpeningDate;

    @Column(name = "account_accountNumber")          //      شماره حساب
    private String accountNumber;

    @Column(name = "account_accountOwner")           //      صاحب حساب
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private String accountOwner;

    @Column(name = "account_bankAndBranch")          //      بانک و شعبه
    private String bankAndBranch;

    @Column(name = "account_accountType")            //      نوع حساب
    private String accountType;

    @Column(name = "account_AccountStatus")          //      وضعیت حساب
    private String AccountStatus;

    @Column(name = "account_Balance")                //      موجودی حساب
    private int Balance;

    @Column(name = "account_Deleted")                //      حذف بانک
    private Boolean deleted;
}