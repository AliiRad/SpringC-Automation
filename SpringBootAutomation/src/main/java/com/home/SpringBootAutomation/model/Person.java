package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.Enum.Gender;
import com.home.SpringBootAutomation.Enum.IsMarried;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString


@Entity(name = "PersonEntity")
@Table(name = "Person_tbl")


public class Person {

	//شناس نامه

	@Id
	@SequenceGenerator(name = "personSeq", sequenceName = "person_seq" , initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "person_id")
	private long id;

	@Column(name ="person_userName")
	private String userName;

	@Column(name ="person_password")
	private String password;

//	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Name")
	@Column(name ="person_name")
	private String name;

//	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Last Name")
	@Column(name ="person_lastname")
	private String lastname;

//	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid CertificateID")
	@Column(name ="person_certificateID")
	private String certificateID; //شماره شناس نامه


//	@Pattern(regexp = "^[0-9\\s]{10}$", message = "Invalid NationalID")
	@Column(name = "person_nationalID")
	private String nationalId; // شماره ملی

//	@PastOrPresent(message = "Invalid birthDate ")
	@Column(name ="person_birthdate")
	private LocalDate birthdate;

//	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid City")
	@Column(name ="person_city")
	private String city; // شهر

//	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid province")
	@Column(name ="person_province")
	private String province; //شهرستان

	@Column(name ="person_gender")
	private Gender gender; // Enum or String?

	@Column(name ="person_isMarried")
	private IsMarried isMarried; //Enum

//	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid FathersName")
	@Column(name ="person_fathersName")
	private String fathersName;

	@Column(name ="person_status")
	private boolean status;//زنده(False) یا مرده(True)؟

	@Column(name ="person_dead")
	private boolean deleted;//زنده یا مرده؟



}