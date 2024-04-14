package com.home.SpringBootAutomation.Model;

import com.home.SpringBootAutomation.Enum.Gender;
import com.home.SpringBootAutomation.Enum.IsMarried;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Table(name = "Person_tbl")
@Entity(name = "PersonEntity")


public class PersonModel {

	//شناس نامه

	@Id
	@SequenceGenerator(name = "personSeq", sequenceName = "person_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "personSeq")
	@Column(name = "person_id",length = 20, nullable = false)
	private long id;

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Name")
	@Column(name ="person_name" ,length =20 )
	private String name;

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Last Name")
	@Column(name ="person_lastname" ,length =20 )
	private String lastname;


	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid CertificateID")
	@Column(name ="person_certificateID" ,length =20 )
	private String certificateID; //شماره شناس نامه


	@Pattern(regexp = "^[0-9\\s]{10}$", message = "Invalid NationalID")
	@Column(name = "person_nationalID", length = 10)
	private String nationalId; // شماره ملی

	@PastOrPresent(message = "Invalid birthDate ")
	@Column(name ="person_birthdate")
	private LocalDateTime birthdate;

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid City")
	@Column(name ="person_city" ,length =20 )
	private String city; // شهر

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid province")
	@Column(name ="person_province" ,length =20 )
	private String province; //شهرستان

	@Column(name ="person_gender" ,length =3 )
	private Gender gender; // Enum or String?

	@Column(name ="person_isMarried" ,length =3 )
	private IsMarried isMarried; //Enum

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid FathersName")
	@Column(name ="person_fathersName" ,length =20 )
	private String fathersName;

	@Column(name ="person_dead" ,length =5 )
	private boolean deleted;//زنده یا مرده؟

}