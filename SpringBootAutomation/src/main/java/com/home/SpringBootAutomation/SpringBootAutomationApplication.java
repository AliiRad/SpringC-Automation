package com.home.SpringBootAutomation;

import com.home.SpringBootAutomation.model.*;
import com.home.SpringBootAutomation.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SpringBootAutomationApplication {
    private static PersonRepository personRepository;
    private static UserRepository userRepository;
    private static OrganisationRepository organisationRepository;
    private static SectionRepository sectionRepository;

    private static TicketGroupRepository ticketGroupRepository;

    public SpringBootAutomationApplication(PersonRepository personRepository,UserRepository userRepository, OrganisationRepository organisationRepository,SectionRepository sectionRepository, TicketGroupRepository ticketGroupRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.organisationRepository = organisationRepository;
        this.sectionRepository = sectionRepository;
        this.ticketGroupRepository = ticketGroupRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutomationApplication.class, args);

        Organisation organisation = Organisation.builder().name("mft").title("mft").build();
        organisationRepository.save(organisation);

        Section section1 = Section.builder().title("edari").organisation(organisation).build();
        Section section2 = Section.builder().title("mali").organisation(organisation).build();
        sectionRepository.save(section1);
        sectionRepository.save(section2);

        TicketGroup ticketGroup1 = TicketGroup.builder().title("mali").build();
        ticketGroupRepository.save(ticketGroup1);

        TicketGroup ticketGroup2 = TicketGroup.builder().title("hesabdari").parent(ticketGroup1).build();
        ticketGroupRepository.save(ticketGroup2);


        Person person1 = Person.builder().name("admin").lastname("admin").certificateId("1231231231").nationalId("1231231231").fathersName("admin").birthdate(LocalDate.now()).build();
        personRepository.save(person1);
        Person person2 = Person.builder().name("user").lastname("user").certificateId("1231231232").nationalId("1231231232").fathersName("user").birthdate(LocalDate.now()).build();
        personRepository.save(person2);

        User user1 = User.builder().username("admin").password("admin").build();
        user1.setPerson(person1);

        User user2 = User.builder().username("user").password("user").build();
        user2.setPerson(person2);

        userRepository.save(user1);
        userRepository.save(user2);
    }

}
