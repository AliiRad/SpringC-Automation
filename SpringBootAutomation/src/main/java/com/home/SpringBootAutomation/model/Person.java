package com.home.SpringBootAutomation.model;


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
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "personEntity")
@Table(name = "person_tbl")


public class Person {
	//------------------------------------------------------

	@Id
	@SequenceGenerator(name = "personSeq", sequenceName = "person_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
	@Column(name = "person_id", nullable = false ,unique = true)
	private Long id;

	//------------------------------------------------------



	@Column(name ="person_name", length = 50 , nullable = false , columnDefinition = "NVARCHAR2(50)")
	@Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Name")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	@NotBlank(message = "Should Not Be Null")
	private String name;

	//------------------------------------------------------


	@Column(name ="person_lastname", length = 50 , nullable = false , columnDefinition = "NVARCHAR2(50)")
	@Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Family")
	@Size(min = 3, max = 50, message = "Last Name must be between 3 and 50 characters")
	@NotBlank(message = "Should Not Be Null")
	private String lastname;

	//------------------------------------------------------


	@Column(name ="person_fathersName" ,length = 50 , nullable = false , columnDefinition = "NVARCHAR2(50)")
	@Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Father Name")
	@Size(min = 3, max = 50, message = "Father Name must be between 3 and 50 characters")
	@NotBlank(message = "Should Not Be Null")
	private String fathersName;

	//------------------------------------------------------


	@Column(name ="person_userName" ,length = 30 , nullable = false , columnDefinition = "VARCHAR2(30)" ,unique = true)
	@Pattern(regexp = "^[a-zA-Z\\s]{3,30}$", message = "Invalid Username")
	//@UniqueElements
	//TODO: Making this field unique using jakarta validation constraints
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
	@NotBlank(message = "Should Not Be Null")
	private String userName;

	//------------------------------------------------------

	@Column(name ="person_password",length = 30 , nullable = false , columnDefinition = "VARCHAR2(30)")
	@Pattern(regexp = "^[a-zA-Z\\s]{8,30}$", message = "Invalid Password")
	@Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
	@NotBlank(message = "Should Not Be Null")
	private String password;

	//------------------------------------------------------

	//TODO: What is certificateID length? Does it Take Characters?
	@Column(name ="person_certificateID",length = 12 , nullable = false , columnDefinition = "VARCHAR2(12)")
	@Pattern(regexp = "^[0-9]{8,12}$", message = "Invalid Certificate ID")
	@Size(min = 8, max = 12, message = " Certificate ID must be between 8 and 12 characters")
	@NotBlank(message = "Should Not Be Null")
	private String certificateID;

	//------------------------------------------------------

	@Column(name = "person_nationalID",length = 12 , nullable = false , columnDefinition = "VARCHAR2(12)" , unique = true)
	@Pattern(regexp = "^[0-9]{8,12}$", message = "Invalid National ID")
    //@UniqueElements
	//TODO: Making this field unique using jakarta validation constraints
	@Size(min = 8, max = 12, message = " National ID must be between 8 and 12 characters")
	@NotBlank(message = "Should Not Be Null")
	private String nationalId;

	//------------------------------------------------------

	@Column(name ="person_birthdate" , nullable = false)
	@Past(message = "Invalid Birth Date")
	private LocalDate birthdate;

	//------------------------------------------------------

	@Column(name ="person_gender", nullable = false)
	@NotNull(message = "Should Not Be Null")
	@Enumerated(EnumType.ORDINAL)
	private GenderEn gender;

	//------------------------------------------------------

	@Column(name ="person_marriageStatus", nullable = false)
	@NotNull(message = "Should Not Be Null")
	@Enumerated(EnumType.ORDINAL)
	private MarriageEn marriageStatus;

	//------------------------------------------------------

	@Column(name ="person_city", length = 50 , nullable = false , columnDefinition = "NVARCHAR2(50)")
	@Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid City Name")
	@Size(min = 3, max = 50, message = "City Name must be between 3 and 50 characters")
	@NotBlank(message = "Should Not Be Null")
	private String city;

	//------------------------------------------------------

	@Column(name ="person_province", length = 50 , nullable = false , columnDefinition = "NVARCHAR2(50)")
	@Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Province Name")
	@Size(min = 3, max = 50, message = "Province Name must be between 3 and 50 characters")
	@NotBlank(message = "Should Not Be Null")
	private String province;

	//------------------------------------------------------

	//TODO: what if we use primitive type boolean ?
	@Column(name = "person_deleted")
	private Boolean deleted = false;


	//------------------------------------------------------

    //Jobs Relationship
	//TODO: Set or List?
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "person")
	private Set<Jobs> jobs;

	//------------------------------------------------------

	//Skills Relationship
	//TODO: Set or List?
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "person")
	private Set<Skills> skills ;

	//------------------------------------------------------

	//TODO: Set or List?
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY ,mappedBy = "person")
	private Set<DrivingLicence>drivingLicences;

	//------------------------------------------------------

	//TODO: Set or List?
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY ,mappedBy = "person")
	private Set<Military>military;

	//------------------------------------------------------






}