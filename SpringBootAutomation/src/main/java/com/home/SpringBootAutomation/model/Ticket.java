package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "ticketEntity")
@Table(name = "ticket_tbl")
public class Ticket {

    @Id
    @SequenceGenerator(name = "ticketSeq" , sequenceName = "ticket_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "ticketSeq")
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "ticket_title" ,  length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
//    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Title")
//    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
//    @NotBlank(message = "Should Not Be Null")
    private String title;


    @Column(name = "ticket_request" ,  length = 255, nullable = false, columnDefinition = "NVARCHAR2(255)")
//    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,255}$", message = "Invalid Request")
//    @Size(min = 3, max = 50, message = "Request must be between 3 and 255 characters")
//    @NotBlank(message = "Should Not Be Null")
    private String request;

    @Column(name = "ticket_time_stamp" ,nullable = false)
    private LocalDateTime ticketTimeStamp;

    @Column(name = "ticket_status")
//    @NotBlank(message = "Should Not Be Null")
//    @Enumerated(EnumType.ORDINAL)
    private String status;

//    private List<Attachment> attachmentList;

    @Column(name = "ticket_active")
    private Boolean deleted;

//    @ManyToOne
//    @JoinColumn(name = "ticket_applicant_id")
//    private Person applicant;

//    @OneToOne(cascade = CascadeType.ALL , mappedBy = "ticket")
//    @JoinColumn(name = "ticket_group_id")
//    private TicketGroup group;
}
