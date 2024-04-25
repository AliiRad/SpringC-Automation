package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    @Column(name = "basic_hourly_pay", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer basicHourlyPay;

    //حقوق پایه روزانه
    @Column(name = "basic_daily_pay", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer basicDailyPay;

    //حقوق پایه ماهانه
    @Column(name = "basic_monthly_pay", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer basicMonthlyPay;

    //بن کارگری
    @Column(name = "working_coupon", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer workingCoupon;

    //حق مسکن
    @Column(name = "housing_right", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer housingRight;

    //پایه سنوات
    @Column(name = "working_year_pay", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer workingYearPay;

    //حق اولاد برای هر فرزند
    @Column(name = "pay_for_each_child", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer payForEachChild;

    //حق عائله مندی افراد متاهل
    @Column(name = "married_people_rights", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer marriedPeopleRights;

    //حق بیمه سهم کارگر
    @Column(name = "insurance", length = 50, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    private Integer insurance;

    //todo max size for year
    @Column(name = "salary_year", length = 6, unique = true, nullable = false)
    @NotBlank(message = "Should Not Be Null")
    @Min(1400)
    private Integer year;

    @Column(name = "salary_deleted")
    private boolean deleted;

}