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

@Entity(name = "timesheetEntity")
@Table(name = "timesheet_tbl",uniqueConstraints = {@UniqueConstraint(columnNames = {"timesheet_employee","timesheet_date"})})
public class Timesheet {
    //timesheet
    @Id
    @SequenceGenerator(name = "timesheetSeq", sequenceName = "timesheet_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_seq")
    @Column(name = "timesheet_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "timesheet_employee")
    private Person employee;

    @Column(name = "timesheet_date")
    @FutureOrPresent(message = "Invalid Timesheet Date")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "timesheet_manager")
    private Person manager;

    //زمان شروع - موظفی
    @Column(name = "timesheet_regular_time_in", columnDefinition = "TIMESTAMP")
    @FutureOrPresent(message = "Invalid Regular Time In")
    private LocalDateTime regularTimeIn;

    //زمان پایان - موظفی
    @Column(name = "timesheet_regular_time_out", columnDefinition = "TIMESTAMP")
    @FutureOrPresent(message = "Invalid Regular Time Out")
    private LocalDateTime regularTimeOut;

    //زمان شروع - اضافه کاری
    @Column(name = "timesheet_over_time_in", columnDefinition = "TIMESTAMP")
    @FutureOrPresent(message = "Invalid Over Time In")
    private LocalDateTime overTimeIn;

    //زمان پایان - اضافه کاری
    @Column(name = "timesheet_over_time_out", columnDefinition = "TIMESTAMP")
    @FutureOrPresent(message = "Invalid Over Time Out")
    private LocalDateTime overTimeOut;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_signature")
    private Attachment employeeSignature;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_signature")
    private Attachment managerSignature;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "timesheet_attachment")
    private List<Attachment> attachments;

    @Column(name = "timesheet_deleted")
    private boolean deleted;


}