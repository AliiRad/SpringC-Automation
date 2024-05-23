package com.home.SpringBootAutomation;

import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.User;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAutomationApplication {
    private static PersonRepository personRepository;
    private static UserRepository userRepository;

    public SpringBootAutomationApplication(PersonRepository personRepository,UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutomationApplication.class, args);

        Person person1 = Person.builder().name("admin").lastname("admin").certificateId("1231231231").nationalId("1231231231").fathersName("admin").build();
        personRepository.save(person1);
        Person person2 = Person.builder().name("user").lastname("user").certificateId("1231231232").nationalId("1231231232").fathersName("user").build();

        User user1 = User.builder().username("admin").password("admin").build();
        user1.setPerson(person1);

        User user2 = User.builder().username("user").password("user").build();
        user2.setPerson(person2);

        userRepository.save(user1);
        userRepository.save(user2);
    }

}
