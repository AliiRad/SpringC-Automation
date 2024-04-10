package com.home.SpringBootAutomation.Model;

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

@Entity(name = "bankEntity")
@Table(name = "bank_tbl")

public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_id",length = 20)
    private Long id;

    @Column(name = "bank_openingDate")            //      تاریخ افتتاح حساب
    private LocalDate accountOpeningDate;

    @Column(name = "bank_accountNumber")          //      شماره حساب
    private String accountNumber;

    @Column(name = "bank_accountOwner")           //      صاحب حساب
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private String accountOwner;

    @Column(name = "bank_bankAndBranch")          //      بانک و شعبه
    private String bankAndBranch;

    @Column(name = "bank_accountType")          //      نوع حساب
    private String accountType;

    @Column(name = "bank_AccountStatus")          //      وضعیت حساب
    private String AccountStatus;

    @Column(name = "bank_Balance")                //      موجودی حساب
    private int Balance;

    @Column(name = "bank_Deleted")                //    حذف بانک
    private Boolean deleted;
}