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


@Table(name = "DrivingLicence_tbl")
@Entity(name ="DrivingLicenceEntity" )


public class DrivingLicence {

	//گواهینامه رانندگی

	//------------------------------------------------------


	@Id
	@SequenceGenerator(name = "drivingLicenceSeq", sequenceName = "drivingLicence_seq" , initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "drivingLicenceSeq")
	@Column(name = "drivingLicence_id",nullable = false , unique = true)
	private Long id;

	//------------------------------------------------------


	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Name")
	@Column(name ="DrivingLicence_name",length = 20,nullable = false,columnDefinition = "NVARCHAR2(20)")
	@Size(min = 1, max = 20, message = "Name must be between 1 and 20 characters")
	@NotBlank(message = "Should Not Be Null")
	private String name;

	//------------------------------------------------------

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Last Name")
	@Column(name ="DrivingLicence_lastname",length = 20,nullable = false,columnDefinition = "NVARCHAR2(20)")
	@Size(min = 1, max = 20, message = "Lastname must be between 1 and 20 characters")
	@NotBlank(message = "Should Not Be Null")
	private String lastname;

	//------------------------------------------------------

	@Pattern(regexp = "^[0-9\\s]{10}$", message = "Invalid NationalID")
	@Column(name ="DrivingLicence_nationalID",length = 10,nullable = false,columnDefinition = "NVARCHAR2(10)")
	@Size(min = 10, max = 10, message = "NationalID must be 10 characters")
	@NotBlank(message = "Should Not Be Null")
	private String nationalId; // شماره ملی

	//------------------------------------------------------

	@Column(name ="DrivingLicence_typeOfCertification", nullable = false)
	@NotBlank(message = "Should Not Be Null")
	@Enumerated(EnumType.ORDINAL)
	private TypeOfCertification typeOfCertification; //نوع گواهی نامه

	//------------------------------------------------------

	@PastOrPresent(message = "Invalid issuance Date")
	@Column(name ="DrivingLicence_issuanceDate",nullable = false)
	private LocalDate issuanceDate;//تاریخ صدور

	//------------------------------------------------------


	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid SerialNumber")
	@Size(min = 1, max = 20, message = "SerialNumber must be between 1 and 20 characters")
	@NotBlank(message = "Should Not Be Null")
	@Column(name ="DrivingLicence_serialNumber",length = 20 , nullable = false ,  columnDefinition = "NVARCHAR2(20)")
	private String serialNumber; // شماره گواهی نامه

	//------------------------------------------------------

	@Column(name ="DrivingLicence_LicenseSuspension",length = 30)
	private boolean licenseSuspension;//تعلیق گواهینامه

	//------------------------------------------------------


	@PastOrPresent(message = "Invalid LicenseSuspension Date")
	@Column(name ="DrivingLicence_LicenseSuspensionDate")
	private LocalDate licenseSuspensionDate;//تاریخ تعلیق گواهینامه

	//------------------------------------------------------

	@FutureOrPresent(message = "Invalid End Date")
	@Column(name ="DrivingLicence_endDate",nullable = false)
	private LocalDate endDate;//تاریخ پایان اعتبار

	//------------------------------------------------------

	@PastOrPresent(message = "Invalid Renewal Date")
	@Column(name ="DrivingLicence_renewal")
	private LocalDate renewal;//تاریخ تمدید مجدد

	//------------------------------------------------------

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY )
    @JoinColumn(name = "DrivingLicence_personId")
    private Person person;

	//------------------------------------------------------

	@Column(name ="DrivingLicence_Deleted",length = 30)
	private boolean deleted;//حذف

	//------------------------------------------------------

}
