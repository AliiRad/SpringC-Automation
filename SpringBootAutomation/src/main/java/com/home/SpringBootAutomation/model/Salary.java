package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;
import java.util.List;

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
    // واحد پول ریال در نظر گرفته شده
    @Id
    @SequenceGenerator(name = "salarySeq" , sequenceName = "salary_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salarySeq")
    @Column(name = "salary_id")
    private Long id;

    //حقوق پایه ساعتی
    //حداقل و حداکثر پول به تومان
    //10,000<x<100,000
    @Column(name = "salary_hourly_pay", columnDefinition = "NUMBER(7)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 100000, message = "Basic Hourly Pay must be at least 100,000")
    @Max(value = 1000000, message = "Basic Hourly Pay must be less than 1,000,000")
    private Integer basicHourlyPay;

    //حقوق پایه روزانه
    //100,000<x<1,000,000
    @Column(name = "salary_daily_pay", columnDefinition = "NUMBER(8)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 1000000, message = "Basic Daily Pay must be at least 1,000,000")
    @Max(value = 10000000, message = "Basic Daily Pay must be less than 10,000,000")
    private Integer basicDailyPay;

    //حقوق پایه ماهانه
    //5,000,000<x<30,000,000
    @Column(name = "salary_monthly_pay", columnDefinition = "NUMBER(9)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 50000000, message = "Basic Monthly Pay must be at least 50,000,000")
    @Max(value = 300000000, message = "Basic Monthly Pay must be less than 300,000,000")
    private Integer basicMonthlyPay;

    //بن کارگری ماهانه
    //1,000,000<x<10,000,000
    @Column(name = "salary_working_coupon", columnDefinition = "NUMBER(9)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 10000000, message = "Working Coupon must be at least 10,000,000")
    @Max(value = 100000000, message = "Working Coupon must be less than 100,000,000")
    private Integer workingCoupon;

    // حق مسکن ماهانه
    //500,000<x<5,000,000
    @Column(name = "salary_housing_right", columnDefinition = "NUMBER(8)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 5000000, message = "Housing Right must be at least 5,000,000")
    @Max(value = 50000000, message = "Housing Right must be less than 50,000,000")
    private Integer housingRight;

    //پایه سنوات برای حداقل یک سال سابقه کار
    //100,000<x<1,000,000
    @Column(name = "salary_working_year", columnDefinition = "NUMBER(8)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 1000000, message = "Working Year Pay must be at least 1,000,000")
    @Max(value = 10000000, message = "Working Year Pay must be less than 10,000,000")
    private Integer workingYearPay;

    //حق اولاد برای هر فرزند ماهانه
    //500,000<x<5,000,000
    @Column(name = "salary_each_child", columnDefinition = "NUMBER(8)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 5000000, message = "Pay For Each Child must be at least 5,000,000")
    @Max(value = 50000000, message = "Pay For Each Child must be less than 50,000,000")
    private Integer payForEachChild;

    //حق عائله مندی افراد متاهل ماهانه
    //300,000<x<5,000,000
    @Column(name = "salary_married_rights", columnDefinition = "NUMBER(8)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 3000000, message = "Married People Rights must be at least 3,000,000")
    @Max(value = 50000000, message = "Married People Rights must be less than 50,000,000")
    private Integer marriedPeopleRights;

    //حق بیمه سهم کارگر ماهانه
    //500,000<x<5,000,000
    @Column(name = "salary_insurance", columnDefinition = "NUMBER(10)")
    @NotNull(message = "Should Not Be Null")
    @Min(value = 5000000, message = "Insurance must be at least 5,000,000")
    @Max(value = 50000000, message = "Insurance must be less than 50,000,000")
    private Integer insurance;

    //TODO: max size for year
    @Column(name = "salary_year", columnDefinition = "NUMBER(8)", unique = true)
    @NotNull(message = "Should Not Be Null")
    @Min(value = 1400, message = "Year must be at least 1400")
    private Integer year;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_attachment")
    private List<Attachment> attachments;

    @Column(name = "salary_deleted")
    private boolean deleted;

}