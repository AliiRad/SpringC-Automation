package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "timesheetEntity")
@Table(name = "timesheet_tbl",uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_id","t_date"})})
public class Timesheet {

    //timesheet

    @Id
    @SequenceGenerator(name = "timesheetSeq", sequenceName = "timesheet_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_seq")
    @Column(name = "timesheet_id")
    private Long id;

    @ManyToOne
    @NotBlank(message = "Should Not Be Null")
    private Person employee;

    @Column(name = "timesheet_date")
    @NotBlank(message = "Should Not Be Null")
    private LocalDate date;

    @ManyToOne
    private Person manager;

    //زمان شروع - موظفی
    @Column(name = "regular_time_in",nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private LocalDateTime regularTimeIn;

    //زمان پایان - موظفی
    @Column(name = "regular_time_out",nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private LocalDateTime regularTimeOut;

    //زمان شروع - اضافه کاری
    @Column(name = "over_time_in",nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private LocalDateTime overTimeIn;

    //زمان پایان - اضافه کاری
    @Column(name = "over_time_out",nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private LocalDateTime overTimeOut;

    @ManyToOne
    private Attachment employeeSignature;

    @ManyToOne
    private Attachment managerSignature;

    @Column(name = "timesheet_deleted")
    private boolean deleted;

}