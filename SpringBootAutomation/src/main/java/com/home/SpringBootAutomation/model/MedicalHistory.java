package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity(name = "medicalEntity")
@Table(name = "medical_tbl")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "m_weight")
    private Float weight;

    @Column(name = "m_height")
    private Integer height;

    @Column(name = "m_bloodPressure")
    private String bloodPressure;

    @Column(name = "m_heartRate")
    private Integer heartRate;

    @Column(name = "m_allergy")
    private String allergy;

    @Column(name = "m_surgery")
    private String surgery;

    @Column(name = "m_emergencyDrug")
    private String emergencyDrug;

    @Column(name = "m_disease")
    private List<Disease> diseaseList;

    @Column(name = "m_emergencyPhoneNumber")
    private String emergencyPhoneNumber;

}
