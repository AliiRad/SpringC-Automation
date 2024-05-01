package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "presenceEntity")
@Table(name = "presence_tbl")
public class PresenceAndAbsence {
    //table for employee presence and absence management
    //حضور و غیاب کاررمندان

    @Id
    @SequenceGenerator(name = "presenceSeq", sequenceName = "presence_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presence_seq")
    @Column(name = "presence_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "presence_employee")
    private Person employee;

    @Column(name = "presence_date")
    @FutureOrPresent(message = "Invalid PresenceAndAbsence Date")
    private LocalDate date;

    @Column(name = "presence_enter_time", columnDefinition = "TIMESTAMP")
    @FutureOrPresent(message = "Invalid Enter Time")
    private LocalDateTime enterTime;

    @Column(name = "presence_exit_time", columnDefinition = "TIMESTAMP")
    @FutureOrPresent(message = "Invalid Exit Time")
    private LocalDateTime exitTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "presence_attachment")
    private List<Attachment> attachments;

    @Column(name = "presence_deleted")
    private boolean deleted;

}
