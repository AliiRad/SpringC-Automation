package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "salaryEntity")
@Table(name = "salary_tbl")
public class Salary implements Serializable {
    //جدول حقوق سالانه

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salary_seq")
    @SequenceGenerator(name = "salary_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    //حقوق پایه ساعتی
    @Column(name = "basic_hourly_pay")
    private Integer basicHourlyPay;

    //حقوق پایه روزانه
    @Column(name = "basic_daily_pay")
    private Integer basicDailyPay;

    //حقوق پایه ماهانه
    @Column(name = "basic_monthly_pay")
    private Integer basicMonthlyPay;

    //بن کارگری
    @Column(name = "working_coupon")
    private Integer workingCoupon;

    //حق مسکن
    @Column(name = "housing_right")
    private Integer housingRight;

    //پایه سنوات
    @Column(name = "working_year_pay")
    private Integer workingYearPay;

    //حق اولاد برای هر فرزند
    @Column(name = "pay_for_each_child")
    private Integer payForEachChild;

    //حق عائله مندی افراد متاهل
    @Column(name = "married_people_rights")
    private Integer marriedPeopleRights;

    //حق بیمه سهم کارگر
    @Column(name = "insurance")
    private Integer insurance;

    @Column(name = "year", length = 4, unique = true, nullable = false)
    @Min(1350)
    @Max(1490)
    @NotNull(message = "fill the field")
    private Integer year;

    @Column(name = "salary_deleted")
    private Boolean deleted = false;

}