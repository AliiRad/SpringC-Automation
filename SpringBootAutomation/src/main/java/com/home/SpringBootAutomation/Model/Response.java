package com.home.SpringBootAutomation.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity(name = "responseEntity")
@Table(name = "response_tbl")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "r_responder" )
    private String responder;

    @Column(name = "r_response" )
    private String ticketResponse;

    @Column(name = "r_date" )
    private LocalDateTime timeStamp;

    @Column(name = "r_active")
    private Boolean active;

}
