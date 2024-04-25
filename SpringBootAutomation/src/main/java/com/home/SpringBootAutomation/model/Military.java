package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.MilitaryExemption;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name = "military_tbl")
@Entity(name = "militaryEntity")
//پایان خدمت سربازی
public class Military {

    @Id
    @SequenceGenerator(name = "militarySeq", sequenceName = "military_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "militarySeq")
    @Column(name = "military_id", unique = true)
    private Long id;

    @Column(name = "military_exemption")
    @NotBlank(message = "Should Not Be Null")
    @Enumerated(EnumType.ORDINAL)
    private MilitaryExemption exemption; //نوع معافیت

    @Column(name = "military_serial_number" , columnDefinition = "VARCHAR2(20)")
    @Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid SerialNumber")
    @Size(min = 1, max = 20, message = "SerialNumber must be between 1 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String serialNumber;


    @Column(name = "military_issuance_date")
    @PastOrPresent(message = "Invalid issuance Date")
    private LocalDate issuanceDate;//تاریخ صدور

    @Column(name = "military_vitiation")
//   TODO: Can We User @AssertFalse ?
    private boolean militaryVitiation;//ابطال شده

    @Column(name = "military_vitiation_date")
    @PastOrPresent(message = "Invalid vitiationDate Date")
    private LocalDate vitiationDate;//تاریخ ابطال

    @Column(name = "military_vitiation_status", columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[A-Za-zا-ی\\s]{3,100}$", message = "Invalid Validation Status ")
    @Size(min = 1, max = 20, message = "Validation Status must be between 3 and 100 characters")
    @NotBlank(message = "Should Not Be Null")
    //TODO: What is  it? Description? or enum (status)?
    private String vitiationStatus;//دلیل ابطال

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "military_person_id")
    private Person person;//TODO:چطوری و در کجا شرط بگذاریم که اگر زن باشد نیاز به پایان خدمت ندارد؟؟

    @Column(name = "military_deleted")
    private boolean deleted;
}