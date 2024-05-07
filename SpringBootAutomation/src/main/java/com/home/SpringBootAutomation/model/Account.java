package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.AccountStatus;
import com.home.SpringBootAutomation.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "accountEntity")
@Table(name = "account_tbl")

public class Account {
    @Id
    @SequenceGenerator(name = "accountSeq", sequenceName = "account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSeq")
    @Column(name = "account_id",length = 20)
    private Long id;

    @Column(name = "account_account_number", length = 50)
    @Pattern(regexp = "^\\d{3,30}$", message = "Invalid Account Number")
    @Size(min = 3, max = 50, message = "Account Number must be between 3 and 30 characters")
    private String accountNumber;

    @Column(name = "account_card_number", length = 16)
    @Pattern(regexp = "^\\d{16}$", message = "Invalid Card Number")
    @Size( min = 16 , max = 16, message = "Card Number must be Exactly 16")
    private String cardNumber;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "account_person")
    private Person person;

    @Column(name = "account_bank_and_branch", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Bank And Branch")
    @Size(min = 3, max = 50, message = "Bank And Branch must be between 3 and 50 characters")
    private String bankAndBranch;

    @Column(name = "account_account_type")
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;

    @Column(name = "account_account_status")
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus;

    @Column(name = "account_deleted")
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    private List<FinancialDocument> financialDocuments;
}