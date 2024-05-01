package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "diseaseEntity")
@Table(name="disease_tbl")
public class Disease {

    @Id
    @SequenceGenerator(name = "diseaseSeq", sequenceName = "disease_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diseaseSeq")
    @Column(name = "disease_id")
    private Long Id;

    @Column(name = "disease_type",  columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]{3,100}$", message = "Invalid Type")
    @Size(min = 3, max = 100, message = "Disease type should be less than 100 character")
    @NotBlank(message = "Disease type can not be null")
    private String type;

    @Column(name = "disease_name", columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]{3,100}$", message = "Invalid Name")
    @Size(min = 3, max = 100, message = "Disease name should be less than 100 character")
    @NotBlank(message = "Disease can not be null")
    private String name;

    @Column(name = "disease_grade", length = 1)
    @Pattern(regexp = "^[1,5]$", message = "Invalid Grade")
    @NotBlank(message = "Disease grade can not be null")
    private Integer grade;

    @Column(name = "disease_active")
    private boolean deleted;


}
