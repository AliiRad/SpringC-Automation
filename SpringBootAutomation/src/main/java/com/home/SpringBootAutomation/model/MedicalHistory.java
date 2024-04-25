package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
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

    @Column(name = "medical_weight", length = 5, nullable = false, columnDefinition = "NVARCHAR2(5)")
    @Pattern(regexp = "^{40,200}$", message = "Invalid weight")
    @Size(min = 40, max = 200, message = "Weight is invalid")
    @NotBlank(message = "Should Not Be Null")
    private Integer weight;

    @Column(name = "medical_height", length = 5, nullable = false, columnDefinition = "NVARCHAR2(5)")
    @Pattern(regexp = "^{140,200}$", message = "Invalid height")
    @Size(min = 140, max = 200, message = "Height is invalid")
    @NotBlank(message = "Should Not Be Null")
    private Integer height;

    @Column(name = "medical_bloodPressure", length = 6, nullable = false, columnDefinition = "NVARCHAR2(6)")
    @Pattern(regexp = "^{10,20}$", message = "Invalid weight")
    @Size(min = 10, max = 20, message = "Blood pressure is invalid")
    @NotBlank(message = "Should Not Be Null")
    private String bloodPressure;

    @Column(name = "medical_heartRate", length = 4, nullable = false, columnDefinition = "NVARCHAR2(4)")
    @Pattern(regexp = "^{100,400}$", message = "Invalid weight")
    @Size(min = 100, max = 400, message = "Heart rate is invalid")
    @NotBlank(message = "Should Not Be Null")
    private Integer heartRate;

    @Column(name = "medical_allergy", length = 100, nullable = true, columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]$", message = "Invalid allergy")
    @Size(min = 0, max = 400, message = "Allergy is invalid")
    private String allergy;

    @Column(name = "medical_surgery", length = 100, nullable = true, columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]$", message = "Invalid surgery")
    @Size(min = 0, max = 100, message = "Surgery is invalid")
    private String surgery;

    @Column(name = "medical_emergencyDrug", length = 100, nullable = true, columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی]$", message = "Invalid surgery")
    @Size(min = 0, max = 100, message = "Surgery is invalid")
    private String emergencyDrug;

    @OneToMany
    @Column(name = "medical_disease")
    private List<Disease> diseaseList;

    @Column(name = "medical_emergencyPhoneNumber", length = 15, nullable = true, columnDefinition = "NVARCHAR2(15)")
    @Pattern(regexp = "^[1-9]\\d{2}\\s\\d{3}\\s\\d{4}$", message = "Invalid emergency phone number")
    @Size(min = 0, max = 15, message = "Emergency phone number is invalid")
    private String emergencyPhoneNumber;

}
