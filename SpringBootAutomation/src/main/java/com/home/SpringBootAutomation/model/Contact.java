package com.home.SpringBootAutomation.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Column(name = "contact_title",length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Title")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String title;

    @Column(name = "contact_province", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Province")
    @Size(min = 3, max = 50, message = "Province must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String province;

    @Column(name = "contact_city", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid City")
    @Size(min = 3, max = 50, message = "City must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String city;

    @Column(name = "contact_fullAddress", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Address")
    @Size(min = 3, max = 50, message = "Address must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String fullAddress;

    @Column(name = "contact_postalCode", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Postal Code")
    @Size(min = 3, max = 50, message = "Postal Code must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private Long postalCode;

    @Column(name = "contact_phoneNumber", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^{3,50}$", message = "Invalid Phone Number")
    @Size(min = 3, max = 50, message = "Phone Number must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private Long phoneNumber;
}
