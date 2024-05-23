package com.home.SpringBootAutomation.model;


import com.google.gson.Gson;
import com.home.SpringBootAutomation.enums.GenderEn;
import com.home.SpringBootAutomation.enums.MarriageEn;
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

@Entity(name = "personEntity")
@Table(name = "person_tbl")
public class Person {
    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "person_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    @Column(name = "person_id")
    private Long id;

    @Column(name = "person_name",  columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String name;

    @Column(name = "person_lastname", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Family")
    @Size(min = 3, max = 50, message = "Last Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String lastname;

    @Column(name = "person_fathers_name", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Father Name")
    @Size(min = 3, max = 50, message = "Father Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String fathersName;


    @Column(name = "person_certificate_id", length = 12)
    @Pattern(regexp = "^[0-9]{1,12}$", message = "Invalid Certificate ID")
    @Size(min = 8, max = 12, message = " Certificate ID must be between 8 and 12 characters")
    @NotBlank(message = "Should Not Be Null")
    private String certificateId;

    @Column(name = "person_national_id", length = 10, unique = true)
    @Pattern(regexp = "^[0-9]{1,10}$", message = "Invalid National ID")
    @Size(min = 1, max = 10, message = " National ID must be between 1 and 10 characters")
    @NotBlank(message = "Should Not Be Null")
    private String nationalId;

    @Column(name = "person_birthdate")
    @Past(message = "Invalid Birth Date")
    private LocalDate birthdate;

    @Column(name = "person_gender")
    @Enumerated(EnumType.ORDINAL)
    private GenderEn gender;

    @Column(name = "person_marriage_status")
    @Enumerated(EnumType.ORDINAL)
    private MarriageEn marriageStatus;

    //    @JsonIgnore
    @Column(name = "person_deleted")
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Jobs> jobs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Skills> skills;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<DrivingLicence> drivingLicences;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Military military;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Account> accounts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<FinancialDocument> financialDocuments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<BankTransaction> bankTransactions;

    @OneToOne(mappedBy = "person")
    private User user;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}