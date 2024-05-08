package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.DocumentType;
import com.home.SpringBootAutomation.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "financialDocumentEntity")
@Table(name = "financial_document_tbl")
public class FinancialDocument {
    @Id
    @SequenceGenerator(name = "financialDocumentSeq", sequenceName = "financialDocument_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocumentSeq")
    @Column(name = "financial_document_id",length = 20)
    private Long id;

    @Column(name = "financial_document_d_date")
    @Past(message = "Invalid Document Date")
    private LocalDate documentDate;

    @Column(name = "financial_document_amount")
//    @Min(1)
//    @Max(50)
    private int amount;

    @Column(name = "financial_document_behalf", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^.{3,50}$", message = "Invalid Behalf")
    @Size(min = 3, max = 50, message = "Behalf must be between 3 and 50 characters")
    private String behalf;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "financial_document_account")
    private Account account;

    @Column(name = "financial_document_t_type")
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @Column(name = "financial_document_d_type")
    @Enumerated(EnumType.ORDINAL)
    private DocumentType documentType;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "financial_document_person")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "financialDocument")
    private List<BankTransaction> bankTransactions;

    @Column(name = "financial_document_deleted")
    private boolean deleted;
}