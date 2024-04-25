package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "timesheetEntity")
//TODO: Check @UniqueConstraint Because of Refactoring .
@Table(name = "timesheet_tbl",uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_id","t_date"})})
public class Timesheet {

    //timesheet
    @Id
    @SequenceGenerator(name = "timesheetSeq", sequenceName = "timesheet_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_seq")
    @Column(name = "timesheet_id")
    private Long id;

    @ManyToOne
    //TODO: cascade = CascadeType.ALL , fetch = FetchType.LAZY
    //TODO:  @JoinColumn
    //@NotBlank(message = "Should Not Be Null") //TODO: Is This Needed?
    private Person employee;

    @Column(name = "timesheet_date")
    //TODO: Put Date Validations
    private LocalDate date;

    @ManyToOne
    //TODO: cascade = CascadeType.ALL , fetch = FetchType.LAZY
    //TODO:  @JoinColumn
    private Person manager;

    //زمان شروع - موظفی
    @Column(name = "timesheet_regular_time_in")
    //TODO: Put Date Validations
    private LocalDateTime regularTimeIn;

    //زمان پایان - موظفی
    @Column(name = "timesheet_regular_time_out")
    //TODO: Put Date Validations
    private LocalDateTime regularTimeOut;

    //زمان شروع - اضافه کاری
    @Column(name = "timesheet_over_time_in")
    //TODO: Put Date Validations
    private LocalDateTime overTimeIn;

    //زمان پایان - اضافه کاری
    @Column(name = "timesheet_over_time_out")
    //TODO: Put Date Validations
    private LocalDateTime overTimeOut;

    @ManyToOne
    //TODO: cascade = CascadeType.ALL , fetch = FetchType.LAZY
    //TODO:  @JoinColumn
    private Attachment employeeSignature;

    @ManyToOne
    //TODO: cascade = CascadeType.ALL , fetch = FetchType.LAZY
    //TODO:  @JoinColumn
    private Attachment managerSignature;

    @Column(name = "timesheet_deleted")
    private boolean deleted;

}