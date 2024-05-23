package com.home.SpringBootAutomation.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "sectionEntity")
@Table(name = "section_tbl")
public class Section {

    @Id
    @SequenceGenerator(name = "sectionSeq", sequenceName = "section_seq" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sectionSeq")
    @Column(name = "section_id")
    private Long id;

    @Column(name = "section_title", length = 40)
    @NotBlank(message = "Should Not Be Null")
    private String title;

    @ManyToOne
    private Organisation organisation;

    @Column(name = "section_duty", length = 100)
    private String duty;

    @Column(name = "section_phone_number", length = 11)
    private String phoneNumber;


    //@OneToMany(mappedBy = "section")
//    private List<Person> Person;

    @Column(name = "section_deleted")
    private boolean deleted;

//    @OneToMany
//    private Attachment attachment;

    @OneToMany
    private List<Section> sectionsPart;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER , mappedBy = "section")
    private Set<User> userSet = new HashSet<>() ; ;

    public List<Section> getSectionsPart() {
        if (sectionsPart == null) {
            sectionsPart = new ArrayList<>();
        }
        return sectionsPart;
    }

    // public List<Person> getUsers() {
    //     if (users == null) {
    //         users = new ArrayList<>();
    //     }
    //     return users;
    // }
    
}
