package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "salaryEntity")
@Table(name = "salary_tbl")
@ApplicationScope
public class Salary implements Serializable {
    //جدول حقوق سالانه
    @Id
    @SequenceGenerator(name = "salarySeq" , sequenceName = "salary_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salarySeq")
    @Column(name = "salary_id")
    private Long id;

    //حقوق پایه ساعتی
    @Column(name = "salary_basic_hourly_pay", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer basicHourlyPay;

    //حقوق پایه روزانه
    @Column(name = "salary_basic_daily_pay", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer basicDailyPay;

    //حقوق پایه ماهانه
    @Column(name = "salary_basic_monthly_pay", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer basicMonthlyPay;

    //بن کارگری
    @Column(name = "salary_working_coupon", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer workingCoupon;

    //حق مسکن
    @Column(name = "salary_housing_right", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer housingRight;

    //پایه سنوات
    @Column(name = "salary_working_year_pay", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer workingYearPay;

    //حق اولاد برای هر فرزند
    @Column(name = "salary_pay_for_each_child", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer payForEachChild;

    //حق عائله مندی افراد متاهل
    @Column(name = "salary_married_people_rights", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer marriedPeopleRights;

    //حق بیمه سهم کارگر
    @Column(name = "salary_insurance", length = 50)
    @NotBlank(message = "Should Not Be Null")
    private Integer insurance;

    //todo max size for year
    @Column(name = "salary_year", length = 6, unique = true)
    @NotBlank(message = "Should Not Be Null")
    @Min(1400)
    private Integer year;

    @Column(name = "salary_deleted")
    private boolean deleted;

}