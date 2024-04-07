package com.home.SpringBootAutomation.model.entity;

import java.time.LocalDate;

import com.home.SpringBootAutomation.Enum.MilitaryExemption;

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
public class MilitaryServiceModel {
	
	//پایان خدمت سربازی
		
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	private long id;
	
	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Name")
	private String name;
	
	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Last Name")
	private String lastname;
	
	private String certificateID; //شماره شنانس نامه
	private String nationalID; // شماره ملی (جفت باید استرینگ باشد! زیرا شماره شناس نامه دارای حروف است)
	private String fathersName;
	private LocalDate birthdate;
	private MilitaryExemption exemption; //نوع معافیت
	private LocalDate issuance;//تاریخ صدور
	private String serialnumber;
	
}
