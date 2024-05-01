package com.home.SpringBootAutomation.model;

import java.util.ArrayList;
import java.util.List;

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

@Entity(name = "organisationEntity")
@Table(name = "organisation_tbl")
public class Organisation {

    @Id
    @SequenceGenerator(name = "organisationSeq", sequenceName = "organisation_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organistationSeq")
    @Column(name = "organisation_id")
    private Long id;

    @Column(name = "organisation_title" , length = 40)
    @NotBlank(message = "Should Not Be Null")
    private String title;

    @Column(name = "organisation_name" , length = 30)
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Name")
    @NotBlank(message = "Should Not Be Null")
    private String name;

//    @OneToMany
//    private Attachment logo;

    @Column(name = "organisation_address" , length = 100)
    @NotBlank(message = "Should Not Be Null")
    private String address;

    @Column(name = "organisation_phone_number" , length = 11)
    @Pattern(regexp = "^[0-9]{3,11}$", message = "Invalid Phone Number")
    @NotBlank(message = "Should Not Be Null")
    private String phoneNumber;

    @Column(name = "organisation_description")
    private String description;
    
    @OneToMany
    private List<Section> sectionList;

    @Column(name = "organisation_deleted")
    private boolean deleted;

    public void addSection(Section section){
    if (sectionList==null){
        sectionList=new ArrayList<>();
    }
        sectionList.add(section);
    }

    
}
