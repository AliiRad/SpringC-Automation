package com.home.SpringBootAutomation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor

@Entity
@Table(name="t_disease")
public class Disease {

    @Id
    private Long Id;

    @Column(name ="d_type")
    private String type;

    @Column(name = "d_name")
    private String name;

    @Column(name = "d_grade")
    private Integer grade;


}
