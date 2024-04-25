package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.AppointmentType;
import com.home.SpringBootAutomation.enums.TypeOfEmployment;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Table(name = "appointment_Decree_tbl")
@Entity(name = "appointmentDecreeEntity")
public class AppointmentDecree {//حکم کارگزینی
    @Id
    @SequenceGenerator(name = "appointmentDecreeSeq", sequenceName = "appointmentDecree_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentDecreeSeq")
    @Column(name = "appointmentDecree_id")
    private Long id;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private Person person;

    @Column(name = "appointmentDecree_typeOfEmployment", nullable = false)
    @NotBlank(message = "Should Not Be Null")
    @Enumerated(EnumType.ORDINAL)
    private TypeOfEmployment typeOfEmployment;                                                      //نوع استخدام

    @Column(name = "appointmentDecree_organizationalUnit", length = 20, nullable = false, columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Organizational Unit")
    @Size(min = 3, max = 20, message = "Organizational Unit must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String organizationalUnit;                                                       //واحد سازمانی

    @Column(name = "appointmentDecree_jobType", length = 20, nullable = false, columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Job Type")
    @Size(min = 3, max = 20, message = "Job Type must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String jobType;                                                                  //نوع کار--کارشناس

    @Column(name = "appointmentDecree_professionalField", length = 20, nullable = false, columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Professional Field")
    @Size(min = 3, max = 20, message = "Professional Field must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String professionalField;                                                        //رسته شغلی--زمینه حرفه

    @Column(name = "appointmentDecree_jobCode", length = 6, nullable = false, columnDefinition = "NVARCHAR2(6)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{6}$", message = "Invalid Job Code")
    @Size(min = 6, max = 6, message = "Invalid Job must be 6 characters")
    @NotBlank(message = "Should Not Be Null")
    private String jobCode;                                                                     //کد شغل

    @Column(name = "appointmentDecree_jobTitle", length = 20, nullable = false, columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Job Title")
    @Size(min = 3, max = 20, message = "Job Title must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String jobTitle;                                                                 //عنوان شغل

    @Column(name = "appointmentDecree_startDate", nullable = false)
    @PastOrPresent(message = "Invalid Start Date")
    private LocalDate startDate;                                                            //تاریخ شروع

    @Column(name = "appointmentDecree_endDate", nullable = false)
    @FutureOrPresent(message = "Invalid End Date")
    private LocalDate endDate;                                                              //تاریخ پایان

    @Column(name = "appointmentDecree_placeOfEmployment", length = 20, nullable = false, columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Place Of Employment")
    @Size(min = 3, max = 20, message = "Place Of Employment must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String placeOfEmployment;                                                        //محل استخدام

    @Column(name = "appointmentDecree_workingHours", length = 20, nullable = false, columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Working Hours")
    @Size(min = 3, max = 20, message = "Working Hours must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String workingHours;                                                             //ساعت کاری

    @Column(name = "appointmentDecree_workingDay", length = 4, nullable = false, columnDefinition = "NVARCHAR2(4)")
    @Min(1)
    @Max(4)
    private Integer workingDay;                                                                  //روز کارکرد

    @Column(name = "appointmentDecree_appointmentType",nullable = false)
    @NotBlank(message = "Should Not Be Null")
    @Enumerated(EnumType.ORDINAL)
    private AppointmentType appointmentType;                                                         //نوع حکم

    @Column(name = "appointmentDecree_appointmentDescription", length = 20, nullable = false, columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Appointment Description")
    @Size(min = 3, max = 20, message = "Appointment Description must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String appointmentDescription;                                                   //شرح حکم

    @Column(name = "appointmentDecree_deleted")
    private Boolean deleted = false;
}
