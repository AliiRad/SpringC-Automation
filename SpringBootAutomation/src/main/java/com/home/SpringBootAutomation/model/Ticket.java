package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity(name = "ticketEntity")
@Table(name = "ticket_tbl")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "t_title")
    private String title;

    @Column(name = "t_group")
    private String group;

    @Column(name = "t_request" )
    private String request;

    @ManyToOne
    @JoinColumn(name = "t_applicant_id")
    private Person applicant;


    @Column(name = "t_timeStamp")
    private LocalDateTime ticketTimeStamp;

    @Column(name = "t_status")
    private Status status;

//    private List<Attachment> attachmentList;

    @Column(name = "t_active")
    private Boolean active;
}
