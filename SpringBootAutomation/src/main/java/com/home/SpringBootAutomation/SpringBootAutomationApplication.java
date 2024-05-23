package com.home.SpringBootAutomation;

import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAutomationApplication.class, args);

		Person person = Person.builder().name("admin").lastname("admin").build();
		User user = User.builder().username("admin").password("admin").build();


	}

}
