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
    @SequenceGenerator(name = "bankSeq", sequenceName = "bank_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "bankSeq")
    private Long id;

    @Column(name = "bank-openingDate")            //      تاریخ افتتاح حساب
    private LocalDateTime accountOpeningDate;

    @Column(name = "bank-accountNumber")          //      شماره حساب
    private String accountNumber;

    @Column(name = "bank-accountOwner")           //      صاحب حساب
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private String accountOwner;

    @Column(name = "bank-accountNumber")          //      بانک و شعبه
    private String bankAndBranch;

    @Column(name = "bank-accountNumber")          //      نوع حساب
    private String accountType;

    @Column(name = "bank-AccountStatus")          //      وضعیت حساب
    private String AccountStatus;

    @Column(name = "bank-Balance")          //      وضعیت حساب
    private int Balance;
}