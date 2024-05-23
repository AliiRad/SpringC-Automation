package com.home.SpringBootAutomation.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@NoArgsConstructor
@Getter
@Setter
public class ProfileDTO {

	private String name;
	private String lastname;
	private String username;
	private Long personId;
	private String nationalId;
	private String certificateId;
	private LocalDate birthdate;

	
	
}
