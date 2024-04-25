package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.MilitaryExemption;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Table(name = "military_tbl")
@Entity(name = "militaryEntity")

public class Military {

    //پایان خدمت سربازی

    @Id
    @SequenceGenerator(name = "militarySeq", sequenceName = "military_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "militarySeq")
    @Column(name = "military_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "military_exemption", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @NotBlank(message = "Should Not Be Null")
    @Enumerated(EnumType.ORDINAL)
    private MilitaryExemption exemption; //نوع معافیت

    @Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid SerialNumber")
    @Column(name = "military_serialNumber", length = 20, nullable = false, columnDefinition = "NVARCHAR2(20)")
    @Size(min = 1, max = 20, message = "SerialNumber must be between 1 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String serialNumber;

    @PastOrPresent(message = "Invalid issuance Date")
    @Column(name = "military_issuanceDate", nullable = false)
    private LocalDate issuanceDate;//تاریخ صدور

    @Column(name = "military_vitiation")
    private boolean militaryVitiation;//ابطال شده

    @PastOrPresent(message = "Invalid vitiationDate Date")
    @Column(name = "military_vitiationDate")
    private LocalDate vitiationDate;//تاریخ ابطال

    @Column(name = "military_vitiationStatus")
    private String vitiationStatus;//دلیل ابطال

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "military_personId")
    private Person person;//چطوری و در کجا شرط بگذاریم که اگر زن باشد نیاز به پایان خدمت ندارد؟؟

    @Column(name = "military_deleted")
    private boolean deleted;
}