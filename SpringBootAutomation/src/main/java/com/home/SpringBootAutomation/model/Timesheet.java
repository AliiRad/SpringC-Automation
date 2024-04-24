package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDate;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_seq")
    @SequenceGenerator(name = "timesheet_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull(message = "please create a person first")
    private Person employee;

    @Column(name = "t_date")
    @NotNull(message = "fill the field")
    private LocalDate date;

    //manager = the one that is responsible for filling this table
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "person_manager_id")
    private Person manager;

    //زمان شروع - موظفی
    @Column(name = "regular_time_in")
    private Timestamp regularTimeIn;

    //زمان پایان - موظفی
    @Column(name = "regular_time_out")
    private Timestamp regularTimeOut;

    //زمان شروع - اضافه کاری
    @Column(name = "over_time_in")
    private Timestamp overTimeIn;

    //زمان پایان - اضافه کاری
    @Column(name = "over_time_out")
    private Timestamp overTimeOut;

    //todo : I dont know the required type for signature
    @Column(name = "employee_signature", unique = true, length = 30)
    private String employeeSignature;

    @Column(name = "manager_signature", unique = true, length = 30)
    private String managerSignature;

    @Column(name = "timesheet_deleted")
    private Boolean deleted = false;

}