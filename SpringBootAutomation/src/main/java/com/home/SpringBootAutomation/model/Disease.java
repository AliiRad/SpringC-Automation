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

    @Column(name = "disease_type", length = 100, nullable = false, columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]$", message = "Invalid Type")
    @Size(min = 0, max = 100, message = "Disease type should be less than 100 character")
    @NotBlank(message = "Disease type can not be null")
    private String type;

    @Column(name = "disease_name", length = 100, nullable = false, columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]$", message = "Invalid Name")
    @Size(min = 0, max = 100, message = "Disease name should be less than 100 character")
    @NotBlank(message = "Disease can not be null")
    private String name;

    @Column(name = "disease_grade", length = 1, nullable = false)
    @Pattern(regexp = "^[1,5]$", message = "Invalid Grade")
    @Size(min = 0, max = 5, message = "Disease grade should between 1-5")
    @NotBlank(message = "Disease grade can not be null")
    private Integer grade;


}
