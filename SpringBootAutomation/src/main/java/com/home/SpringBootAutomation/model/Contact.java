package com.home.SpringBootAutomation.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

public class Contact {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_title",length = 20)
    private String title;

    @Column(name = "contact_province", length = 20)
    private String province;

    @Column(name = "contact_city", length = 20)
    private String city;

    @Column(name = "contact_fullAddress", length = 20)
    private String fullAddress;

    @Column(name = "contact_postalCode", length = 20)
    private Long postalCode;

    @Column(name = "contact_phoneNumber", length = 20)
    private Long phoneNumber;
}
