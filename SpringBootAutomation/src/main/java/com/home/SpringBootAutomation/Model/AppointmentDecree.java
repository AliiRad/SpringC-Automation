package com.home.SpringBootAutomation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
@Table(name = "appointment_Decree_tbl")
@Entity(name = "appointmentDecreeEntity")
public class AppointmentDecree {//حکم کارگزینی
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointmentDecree_id",length = 20)
    private Long id;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private PersonModel person;

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Type Of Employment")
    @Column(name ="appointmentDecree_typeOfEmployment" ,length =20 )
    private String typeOfEmployment;                                                         //نوع استخدام

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Organizationa lUnit")
    @Column(name ="appointmentDecree_organizationalUnit" ,length =20 )
    private String organizationalUnit;                                                       //واحد سازمانی

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Job Type")
    @Column(name ="appointmentDecree_jobType" ,length =20 )
    private String jobType;                                                                  //نوع کار--کارشناس

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Professional Field")
    @Column(name ="appointmentDecree_professionalField" ,length =20 )
    private String professionalField;                                                        //رسته شغلی--زمینه حرفه

//    @Pattern(regexp = "^{1,5}$",message = "Invalid Job Code")
    @Column(name ="appointmentDecree_jobCode" ,length =5 )
    private int jobCode;                                                                     //کد شغل

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Job Title")
    @Column(name ="appointmentDecree_jobTitle" ,length =20 )
    private String jobTitle;                                                                 //عنوان شغل

//    @PastOrPresent(message = "Invalid Start Date")
    @Column(name ="appointmentDecree_startDate")
    private LocalDateTime startDate;                                                         //تاریخ شروع

//    @FutureOrPresent(message = "Invalid End Date")
    @Column(name ="appointmentDecree_endDate")
    private LocalDateTime endDate;                                                           //تاریخ پایان

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Place Of Employment")
    @Column(name ="appointmentDecree_placeOfEmployment" ,length =20 )
    private String placeOfEmployment;                                                        //محل استخدام

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Working Hours")
    @Column(name ="appointmentDecree_workingHours" ,length =20 )
    private String workingHours;                                                             //ساعت کاری

//    @Pattern(regexp = "^{1,4}$",message = "Invalid Working Day")
    @Column(name ="appointmentDecree_workingDay" ,length =4 )
    private int workingDay;                                                                  //روز کارکرد

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Appointment Type")
    @Column(name ="appointmentDecree_appointmentType" ,length =20 )
    private String appointmentType;                                                          //نوع حکم

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Appointment Description")
    @Column(name ="appointmentDecree_appointmentDescription" ,length =20 )
    private String appointmentDescription;                                                   //شرح حکم
}
