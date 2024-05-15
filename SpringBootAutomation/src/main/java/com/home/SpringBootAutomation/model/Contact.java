package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.Base;
import com.home.SpringBootAutomation.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter


@Entity(name = "contactEntity")
@Table(name = "contact_tbl")
public class Contact extends Base {

    @Id
    @SequenceGenerator(name = "contactSeq", sequenceName = "contact_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Long id;

    @Column(name = "contact_title",columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Title")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String title;

    @Column(name = "contact_province", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Province")
    @Size(min = 3, max = 50, message = "Province must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String province;

    @Column(name = "contact_city", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid City")
    @Size(min = 3, max = 50, message = "City must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String city;

    @Column(name = "contact_fullAddress", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Address")
    @Size(min = 3, max = 50, message = "Address must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String fullAddress;

    @Column(name = "contact_postalCode", length = 10)
    @Pattern(regexp = "\\d{10}", message = "Postal Code !")
    @NotBlank(message = "Should Not Be Null")
    private Long postalCode;

    @Column(name = "contact_phoneNumber", columnDefinition = "NVARCHAR2(11)")
    @Pattern(regexp = "^[0-9]{3,11}$", message = "Invalid National ID")
    @NotBlank(message = "Should Not Be Null")
    private String phoneNumber;

    @Column(name = "ticket_time_stamp" )
    private LocalDateTime ticketTimeStamp;

    @Column(name = "contact_status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "contact_deleted")
    private boolean deleted;
}
