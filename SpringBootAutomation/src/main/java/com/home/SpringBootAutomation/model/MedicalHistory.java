package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "medicalEntity")
@Table(name = "medical_tbl")
public class MedicalHistory {
    @Id
    @SequenceGenerator(name="medicalSeq",sequenceName = "medical_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "medicalSeq")
    private Long id;

    @Column(name = "medical_weight", length = 5)
    @Pattern(regexp = "^{40,200}$", message = "Invalid weight")
    @NotBlank(message = "Should Not Be Null")
    private Integer weight;

    @Column(name = "medical_height", length = 5)
    @Pattern(regexp = "^{140,300}$", message = "Invalid height")
    @NotBlank(message = "Should Not Be Null")
    private Integer height;

    @Column(name = "medical_blood_pressure", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^{10,20}$", message = "Invalid weight")
    @Size(min = 10, max = 20, message = "Blood pressure is invalid")
    @NotBlank(message = "Should Not Be Null")
    private String bloodPressure;

    @Column(name = "medical_heart_rate", length = 4)
    @Pattern(regexp = "^{100,400}$", message = "Invalid weight")
    @NotBlank(message = "Should Not Be Null")
    private Integer heartRate;

    @Column(name = "medical_allergy", columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]{3,100}$", message = "Invalid allergy")
    @Size(min = 3, max = 100, message = "Allergy is invalid")
    private String allergy;

    @Column(name = "medical_surgery", columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]{3,100}$", message = "Invalid surgery")
    @Size(min = 3, max = 100, message = "Surgery is invalid")
    private String surgery;

    @Column(name = "medical_emergency_drug", length = 100, columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]{3,100}$", message = "Invalid surgery")
    @Size(min = 3, max = 100, message = "Surgery is invalid")
    private String emergencyDrug;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "medical_disease")
    private List<Disease> diseaseList;

    @Column(name = "medical_emergency_phone_number", length = 15)
    @Pattern(regexp = "^[1-9]\\d{2}\\s\\d{3}\\s\\d{4}$", message = "Invalid emergency phone number")
    @Size(min = 15, max = 15, message = "Emergency phone number is invalid")
    private String emergencyPhoneNumber;

    @Column(name = "medical_active")
    private boolean deleted;

    @OneToOne
    @JoinColumn(name = "medical_person")
    private Person person;

}
