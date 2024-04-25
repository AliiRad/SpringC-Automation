package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.DocumentType;
import com.home.SpringBootAutomation.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "FinancialDocumentEntity")
@Table(name = "FinancialDocument_tbl")
public class FinancialDocument {
    @Id
    @SequenceGenerator(name = "financialDocumentSeq", sequenceName = "financialDocument_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocumentSeq")
    @Column(name = "financialDocument_id",length = 20)
    private Long id;

    @Column(name = "financialDocument_documentDate", nullable = false)
    @Past(message = "Invalid Document Date")
    private LocalDate documentDate;

    @Column(name = "financialDocument_amount", length = 50, nullable = false)
    @Min(1)
    @Max(50)
    private int amount;

    @Column(name = "financialDocument_behalf", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^{3,50}$", message = "Invalid Behalf")
    @Size(min = 3, max = 50, message = "Behalf must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String behalf;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "financialDocument_account")
    private Account account;

    @Column(name = "financialDocument_transactionType", nullable = false)
    @NotNull(message = "Should Not Be Null")
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @Column(name = "financialDocument_documentType", nullable = false)
    @NotNull(message = "Should Not Be Null")
    @Enumerated(EnumType.ORDINAL)
    private DocumentType documentType;

    @Column(name = "financialDocument_deleted")
    private Boolean deleted;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "financialDocument_customer")
    private Person customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "financialDocument")
    private List<BankTransaction> bankTransactions;
}