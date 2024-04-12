package com.home.SpringBootAutomation.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@SuppressWarnings("ALL")

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "jobsEntity")
@Table(name = "jobs_tbl")
public class Jobs {

    @Id
    @SequenceGenerator(name = "jobsSeq", sequenceName = "jobs_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobsSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "company_name" , length = 30 , nullable = false)
    @Pattern(regexp = "^[a-zA-Z\\s]{3,30}$", message = "Invalid Name")
    private String companyName;

    @Column(name = "job_address" , length = 50, nullable = false)
    @Pattern(regexp = "^[a-zA-Z ,\\s]{3,30}$", message = "Invalid Address")
    private String address;

    @Column(name = "job_post" , length = 30)
    @NotBlank

//    private List<@NotBlank String> positions;
//    private List<String> positions;
    private String positions;


    @Column(name = "start_date" , nullable = false)
    @Past(message = "Invalid Start Date")
    private LocalDate startDate;

    @Column(name = "end_date" , nullable = false )
    @Past(message = "Invalid End Date")
    private LocalDate endDate;

    @Column(name = "deleted" , length = 30)
    private Boolean deleted = false;

//    @ManyToOne()
//    private Person person ;

//    @OneToMany(mappedBy = "")
//    private List<Attachment> attachment;

//    List<@NotBlank Attachment> attachment;

}
