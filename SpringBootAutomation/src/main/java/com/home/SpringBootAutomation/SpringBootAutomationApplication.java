package com.home.SpringBootAutomation;

import com.home.SpringBootAutomation.model.Organisation;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.model.Section;
import com.home.SpringBootAutomation.model.User;
import com.home.SpringBootAutomation.repository.OrganisationRepository;
import com.home.SpringBootAutomation.repository.PersonRepository;
import com.home.SpringBootAutomation.repository.SectionRepository;
import com.home.SpringBootAutomation.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAutomationApplication {
    private static PersonRepository personRepository;
    private static UserRepository userRepository;
    private static OrganisationRepository organisationRepository;
    private static SectionRepository sectionRepository;

    public SpringBootAutomationApplication(PersonRepository personRepository,UserRepository userRepository, OrganisationRepository organisationRepository,SectionRepository sectionRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.organisationRepository = organisationRepository;
        this.sectionRepository = sectionRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutomationApplication.class, args);

        Organisation organisation = Organisation.builder().name("mft").build();
        organisationRepository.save(organisation);

        Section section1 = Section.builder().title("edari").organisation(organisation).build();
        Section section2 = Section.builder().title("mali").organisation(organisation).build();
        sectionRepository.save(section1);
        sectionRepository.save(section2);



        Person person1 = Person.builder().name("admin").lastname("admin").certificateId("1231231231").nationalId("1231231231").fathersName("admin").build();
        personRepository.save(person1);
        Person person2 = Person.builder().name("user").lastname("user").certificateId("1231231232").nationalId("1231231232").fathersName("user").build();
        personRepository.save(person2);

        User user1 = User.builder().username("admin").password("admin").build();
        user1.setPerson(person1);

        User user2 = User.builder().username("user").password("user").build();
        user2.setPerson(person2);

        userRepository.save(user1);
        userRepository.save(user2);
    }

}
