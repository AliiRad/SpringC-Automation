package com.home.SpringBootAutomation.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Every Other Model should be Implemented in here
 * Getters and Setters 
 * https://www.javatpoint.com/dto-java
 */

@Component
@NoArgsConstructor
@Getter
@Setter
public class ProfileDTO {

	private String name;
	private String lastname;
	private String username;
	private Long personID;
	private String nationalID;
	private String certificateID;
	private LocalDate birthdate;

	
	
}
