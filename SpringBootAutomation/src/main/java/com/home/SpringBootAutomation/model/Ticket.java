package com.home.SpringBootAutomation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.SpringBootAutomation.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "ticketEntity")
@Table(name = "ticket_tbl")
public class Ticket {

    @Id
    @SequenceGenerator(name = "ticketSeq", sequenceName = "ticket_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketSeq")
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "ticket_title", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s\\d]{3,50}$", message = "Invalid Title")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String title;


    @Column(name = "ticket_request", columnDefinition = "NVARCHAR2(255)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s\\d]{3,255}$", message = "Invalid Request")
    @Size(min = 3, max = 50, message = "Request must be between 3 and 255 characters")
    @NotBlank(message = "Should Not Be Null")
    private String request;

    @Column(name = "ticket_time_stamp")
//    @FutureOrPresent
    private LocalDateTime ticketTimeStamp;

    @Column(name = "ticket_status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "ticket_attachment_id")
//    private List<Attachment> attachmentList;

    @Column(name = "ticket_active")
    private boolean deleted;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "ticket_applicant_id")
//    private User applicant;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_group_id")
    private TicketGroup group;

    @JsonIgnore
    @OneToOne(mappedBy = "ticket",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Response response;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_section_id")
    private Section section;
}
