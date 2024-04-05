package com.home.SpringBootAutomation.Model;

import java.time.LocalDate;

import com.home.SpringBootAutomation.Enum.TypeOfCertification;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class DrivingLicenceModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	private long id;
	
	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Name")
	private String name;
	
	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Last Name")
	private String lastname;
	private String fathersName;
	private String nationalID; // شماره ملی
	private LocalDate birthdate;
	private TypeOfCertification typeOfCertification; //نوع گواهی نامه
	private LocalDate issuance;//تاریخ صدور
	private String serialnumber; // شماره گواهی نامه
}
