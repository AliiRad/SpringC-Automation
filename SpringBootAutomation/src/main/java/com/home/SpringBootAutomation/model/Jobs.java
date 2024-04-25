package com.home.SpringBootAutomation.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;




@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "jobsEntity")
@Table(name = "jobs_tbl")
public class Jobs {

    @Id
    @SequenceGenerator(name = "jobsSeq", sequenceName = "jobs_seq",  allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobsSeq")
    @Column(name = "job_id", nullable = false, unique = true)
    private Long id;


    @Column(name = "job_companyName", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Name")
    @Size(min = 3, max = 50, message = "Company Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String companyName;

    @Column(name = "job_address",  columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی ,\\s]{3,100}$", message = "Invalid Address")
    @Size(min = 3, max = 100, message = "Address must be between 3 and 100 characters")
    @NotBlank(message = "Should Not Be Null")
    private String address;


    @Column(name = "job_post", columnDefinition = "NVARCHAR2(30)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Position Names")
    @Size(min = 3, max = 50, message = "Position Names must be between 3 and 30 characters")
    @NotBlank(message = "Should Not Be Null")
    private String positions;

    @Column(name = "job_startDate", nullable = false)
    @Past(message = "Invalid Start Date")
    private LocalDate startDate;

    @Column(name = "job_endDate", nullable = false)
    @Past(message = "Invalid End Date")
    private LocalDate endDate;

    @Column(name = "job_deleted")
    private Boolean deleted = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_personId")
    private Person person;

}
