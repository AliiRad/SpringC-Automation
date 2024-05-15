package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.AppointmentType;
import com.home.SpringBootAutomation.enums.TypeOfEmployment;
import jakarta.persistence.*;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//حکم کارگزینی


@Entity(name = "appointmentDecreeEntity")
@Table(name = "appointment_decree_tbl")
public class AppointmentDecree {
    @Id
    @SequenceGenerator(name = "appointmentDecreeSeq", sequenceName = "appointmentDecree_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentDecreeSeq")
    @Column(name = "appointment_decree_id")
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_decree_person")
    private Person person;

    @Column(name = "appointment_decree_type_of_employment" )
    @Enumerated(EnumType.ORDINAL)
    private TypeOfEmployment typeOfEmployment;                                                      //نوع استخدام

    @Column(name = "appointment_decree_organizational_unit", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Organizational Unit")
    @Size(min = 3, max = 20, message = "Organizational Unit must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String organizationalUnit;                                                       //واحد سازمانی

    @Column(name = "appointment_decree_job_type", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Job Type")
    @Size(min = 3, max = 20, message = "Job Type must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String jobType;                                                                  //نوع کار--کارشناس

    @Column(name = "appointment_decree_professional_field",columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Professional Field")
    @Size(min = 3, max = 20, message = "Professional Field must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String professionalField;                                                        //رسته شغلی--زمینه حرفه

    @Column(name = "appointment_decree_job_code", columnDefinition = "NVARCHAR2(6)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{6}$", message = "Invalid Job Code")
    @Size(min = 6, max = 6, message = "Invalid Job must be 6 characters")
    @NotBlank(message = "Should Not Be Null")
    private String jobCode;                                                                     //کد شغل

    @Column(name = "appointment_decree_job_title",  columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Job Title")
    @Size(min = 3, max = 20, message = "Job Title must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String jobTitle;                                                                 //عنوان شغل

    @Column(name = "appointment_decree_start_date")
    @PastOrPresent(message = "Invalid Start Date")
    private LocalDate startDate;                                                            //تاریخ شروع

    @Column(name = "appointment_decree_end_date")
    @FutureOrPresent(message = "Invalid End Date")
    private LocalDate endDate;                                                              //تاریخ پایان

    @Column(name = "appointment_decree_place_of_employment",  columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Place Of Employment")
    @Size(min = 3, max = 20, message = "Place Of Employment must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String placeOfEmployment;                                                        //محل استخدام

    @Column(name = "appointment_decree_working_hours", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Working Hours")
    @Size(min = 3, max = 20, message = "Working Hours must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String workingHours;                                                             //ساعت کاری

    @Column(name = "appointment_decree_working_day")
//    @Min(1)
//    @Max(4)
    private Integer workingDay;                                                                  //روز کارکرد

    @Column(name = "appointment_decree_appointment_type")
    @Enumerated(EnumType.ORDINAL)
    private AppointmentType appointmentType;                                                         //نوع حکم

    @Column(name = "appointment_decree_appointment_description", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Appointment Description")
    @Size(min = 3, max = 20, message = "Appointment Description must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String appointmentDescription;                                                   //شرح حکم

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_decree_attachment_list")
    private List<Attachment> attachmentList;

    @Column(name = "appointment_decree_deleted")
    private boolean deleted;
}
