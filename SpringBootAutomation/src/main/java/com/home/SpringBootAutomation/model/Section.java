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

    @Column(name = "section_title")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Title")
    @Size(min = 3, max = 50, message = "title must be between 3 and 50 characters")
    @NotBlank(message = "title Should Not Be Null")
    private String title;

    @ManyToOne
    private Organisation organisation;

    @Column(name = "section_duty")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Duty")
    @Size(min = 3, max = 50, message = "duty must be between 3 and 50 characters")
    @NotBlank(message = "duty Should Not Be Null")
    private String duty;

    @Column(name = "section_phoneNumber")
    @Pattern(regexp = "^[0-9]{3,11}$", message = "Invalid Phone Number")
    @NotBlank(message = "phoneNumber Should Not Be Null")
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
    private Set<User> userSet = new HashSet<>() ;

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
