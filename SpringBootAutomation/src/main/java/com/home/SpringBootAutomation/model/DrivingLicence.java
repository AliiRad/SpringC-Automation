package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.TypeOfCertification;
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

@Entity(name = "drivingLicenceEntity")
@Table(name = "driving_licence_tbl")
public class DrivingLicence {
    @Id
    @SequenceGenerator(name = "drivingLicenceSeq", sequenceName = "drivingLicence_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drivingLicenceSeq")
    @Column(name = "driving_licence_id")
    private Long id;

    @Column(name = "driving_licence_type_of_cert")
    @NotBlank(message = "Should Not Be Null")
    @Enumerated(EnumType.ORDINAL)
    private TypeOfCertification typeOfCertification; //نوع گواهی نامه

    @Column(name = "driving_licence_issuance_date")
    @PastOrPresent(message = "Invalid issuance Date")
    private LocalDate issuanceDate;//تاریخ صدور

    @Column(name = "driving_licence_serial_number", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid SerialNumber")
    //TODO: Does it take character or only digit?
    @Size(min = 1, max = 20, message = "SerialNumber must be between 1 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String serialNumber; // شماره گواهی نامه

    @Column(name = "driving_licence_license_suspension")
    //TODO: Add @AssertFalse or @AssertTrue if needed.
    private boolean licenseSuspension;//تعلیق گواهینامه

    @Column(name = "driving_licence_license_suspension_date")
    @PastOrPresent(message = "Invalid License Suspension Date")
    private LocalDate licenseSuspensionDate;//تاریخ تعلیق گواهینامه

    @Column(name = "driving_licence_end_date")
    @FutureOrPresent(message = "Invalid End Date")
    private LocalDate endDate;//تاریخ پایان اعتبار

    @Column(name = "driving_licence_renewal")
    @PastOrPresent(message = "Invalid Renewal Date")
    private LocalDate renewal;//تاریخ تمدید مجدد

    @Column(name = "driving_licence_deleted")
    private boolean deleted;//حذف

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_licence_person_id")
    private Person person;


}
