package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.TypeOfCertification;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder


@Table(name = "driving_licence_tbl")
@Entity(name = "drivingLicenceEntity")
public class DrivingLicence {
//	//گواهینامه رانندگی

    @Id
    @SequenceGenerator(name = "drivingLicenceSeq", sequenceName = "drivingLicence_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drivingLicenceSeq")
    @Column(name = "driving_licence_id")
    private Long id;

    @Column(name = "drivingLicence_typeOfCertification", nullable = false)
    @NotBlank(message = "Should Not Be Null")
    @Enumerated(EnumType.ORDINAL)
    private TypeOfCertification typeOfCertification; //نوع گواهی نامه

    @PastOrPresent(message = "Invalid issuance Date")
    @Column(name = "drivingLicence_issuanceDate", nullable = false)
    private LocalDate issuanceDate;//تاریخ صدور

    @Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid SerialNumber")
    @Size(min = 1, max = 20, message = "SerialNumber must be between 1 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    @Column(name = "drivingLicence_serialNumber", length = 20, nullable = false)
    private String serialNumber; // شماره گواهی نامه

    @Column(name = "drivingLicence_LicenseSuspension", length = 30)
    private boolean licenseSuspension;//تعلیق گواهینامه

    @PastOrPresent(message = "Invalid LicenseSuspension Date")
    @Column(name = "drivingLicence_LicenseSuspensionDate")
    private LocalDate licenseSuspensionDate;//تاریخ تعلیق گواهینامه

    @FutureOrPresent(message = "Invalid End Date")
    @Column(name = "drivingLicence_endDate", nullable = false)
    private LocalDate endDate;//تاریخ پایان اعتبار

    @PastOrPresent(message = "Invalid Renewal Date")
    @Column(name = "drivingLicence_renewal")
    private LocalDate renewal;//تاریخ تمدید مجدد

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_licence_person_id")
    private Person person;

    @Column(name = "drivingLicence_Deleted", length = 30)
    private boolean deleted;//حذف
}
