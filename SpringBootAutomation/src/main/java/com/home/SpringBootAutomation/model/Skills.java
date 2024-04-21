package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.Enum.SkillsGradeEn;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuppressWarnings("ALL")

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "skillsEntity")
@Table(name = "skills_tbl")
public class Skills {


    @Id
    @SequenceGenerator(name = "skillsSeq", sequenceName = "skills_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skillsSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "skill_title" , length = 30 , nullable = false)
    private String skillTitle;

    //better name.(rate , degree , scale , grade)
    //TODO: Enum Or Not ?
    @Column(name = "skill_rate" , length = 30 , nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SkillsGradeEn rate;


    // better name.(training , education)
    @Column(name = "skill_training" , length = 30 , nullable = false)
    private String training;


    @Column(name = "skill_description" , length = 200, nullable = false)
    @Size(min = 5, max = 200, message
            = "Job Description must be between 5 and 200 characters")
    private String description;


    @Column(name = "skill_certification" , length = 30 , nullable = false)
    private String certification;


    @Column(name = "deleted" , length = 30)
    private Boolean deleted = false;

//    @ManyToOne()
//    private Person person ;

//    @OneToMany(mappedBy = "")
//    private List<Attachment> attachment;



}
