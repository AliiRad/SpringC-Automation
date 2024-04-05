package com.home.SpringBootAutomation.Model;

import java.time.LocalDate;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.home.SpringBootAutomation.Enum.Gender;
import com.home.SpringBootAutomation.Enum.IsMarried;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class PersonModel {

	//شناس نامه
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	private long id;
	
	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Name")
	private String name;	
	
	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Last Name")
	private String lastname;
	
	private String certificateID; //شماره شناس نامه
	private String nationalID; // شماره ملی
	private LocalDate birthdate;
	private String city; // شهر
	private String province; //شهرستان
	private Gender gender; // Enum or String?
	private IsMarried isMarried; //Enum
	private String fathersName;
		
}
