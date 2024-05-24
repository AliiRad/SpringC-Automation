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

    @Column(name = "organisation_title")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Title")
    @Size(min = 3, max = 50, message = "title must be between 3 and 50 characters")
    @NotBlank(message = "title Should Not Be Null")
    private String title;

    @Column(name = "organisation_name",  columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "name Should Not Be Null")
    private String name;

//    @OneToMany
//    private Attachment logo;

    @Column(name = "organisation_address")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Address")
    @Size(min = 3, max = 100, message = "address must be between 3 and 100 characters")
    @NotBlank(message = "address Should Not Be Null")
    private String address;

    @Column(name = "organisation_phoneNumber")
    @Pattern(regexp = "^[0-9]{3,11}$", message = "Invalid Phone Number")
    @NotBlank(message = "phoneNumber Should Not Be Null")
    private String phoneNumber;

    @Column(name = "organisation_description")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Description")
    @Size(min = 3, max = 100, message = "description must be between 3 and 100 characters")
    @NotBlank(message = "description Should Not Be Null")
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
