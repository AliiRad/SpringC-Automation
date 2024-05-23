package com.home.SpringBootAutomation.model;


import com.google.gson.Gson;
import com.home.SpringBootAutomation.enums.GenderEn;
import com.home.SpringBootAutomation.enums.MarriageEn;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "propertyEntity")
@Table(name = "property_tbl")
public class Property {
    @Id
    @SequenceGenerator(name = "propertySeq", sequenceName = "property_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propertySeq")
    @Column(name = "property_id")
    private Long id;

    @Column(name = "property_name",  columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String name;

    @Column(name = "property_brand", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid brand")
    @Size(min = 3, max = 50, message = "brand must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String brand;

    @Column(name = "property_serialNumber", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid serialNumber")
    @Size(min = 3, max = 50, message = "serialNumber must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String serialNumber;

    @Column(name = "property_price",  columnDefinition = "VARCHAR2(30)", unique = true)
    @Pattern(regexp = "^[a-zA-Z1-9\\s]{3,30}$", message = "Invalid price")
    @Size(min = 3, max = 30, message = "price between 3 and 30 characters")
    @NotBlank(message = "Should Not Be Null")
    private String price;

    @Column(name = "property_owner", columnDefinition = "VARCHAR2(30)")
    @Pattern(regexp = "^[a-zA-Z1-9\\s]{8,30}$", message = "Invalid owner")
    @Size(min = 8, max = 30, message = "owner must be between 8 and 30 characters")
    @NotBlank(message = "Should Not Be Null")
    private String owner;

    @Column(name = "property_PropertyNumber", length = 12)
    @Pattern(regexp = "^[0-9]{1,12}$", message = "Invalid Property number")
    @Size(min = 8, max = 12, message = " PropertyNumber must be between 8 and 12 characters")
    @NotBlank(message = "Should Not Be Null")
    private String PropertyNumber;



    @Column(name = "property_deliveryDate")
    @Past(message = "Invalid delivery Date")
    private LocalDate deliveryDate;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
//    private List<Jobs> jobs;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
//    private List<Skills> skills;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
//    private List<DrivingLicence> drivingLicences;
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
//    private Military military;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
//    private List<Account> accounts;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
//    private List<FinancialDocument> financialDocuments;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
//    private List<BankTransaction> bankTransactions;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}