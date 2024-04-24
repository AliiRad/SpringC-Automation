package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.SkillsGradeEn;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

@Entity(name = "skillsEntity")
@Table(name = "skills_tbl")
public class Skills {



    @Id
    @SequenceGenerator(name = "skillsSeq", sequenceName = "skills_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skillsSeq")
    @Column(name = "skill_id", nullable = false)
    private Long id;


    @Column(name = "skill_title" , length = 50 , nullable = false , columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Title")
    @Size(min = 3, max = 50, message = "Skill Title must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String skillTitle;

    @Column(name = "skill_rate" , nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SkillsGradeEn rate;


    @Column(name = "skill_training" , length = 30 , nullable = false , columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,30}$", message = "Invalid Skill Training")
    @Size(min = 3, max = 50, message = "Skill Training must be between 3 and 30 characters")
    @NotBlank(message = "Should Not Be Null")
    private String training;


    @Column(name = "skill_description" , length = 200, nullable = false ,  columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{5,200}$", message = "Invalid Skill Description")
    @Size(min = 5, max = 200, message = "Skill Description must be between 5 and 200 characters")
    private String description;


    @Column(name = "skill_certification" , length = 30 , nullable = false ,  columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,30}$", message = "Invalid Skill Certification")
    @Size(min = 3, max = 50, message = "Certification must be between 3 and 30 characters")
    @NotBlank(message = "Should Not Be Null")
    private String certification;


    @Column(name = "skill_deleted" , length = 30)
    private Boolean deleted = false;


    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinColumn(name = "skill_personId")
    private Person person;


}
