//package com.home.SpringBootAutomation.model;
//
//
//import com.home.SpringBootAutomation.enums.MilitaryExemption;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import lombok.experimental.SuperBuilder;
//
//import java.time.LocalDate;
//
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@SuperBuilder
//@ToString
//
//@Table(name = "Military_tbl")
//@Entity(name = "MilitaryEntity")
//
//
//public class Military {
//
//	//پایان خدمت سربازی
//
//	@Id
//	@SequenceGenerator(name = "militarySeq", sequenceName = "military_seq")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "militarySeq")
//	@Column(name = "military_id")
//	private long id;
//
////	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
////	private Person personModel;//چطوری و در کجا شرط بگذاریم که اگر زن باشد نیاز به پایان خدمت ندارد؟؟
//
////	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Name")
//	@Column(name ="military_name")
//	private String name;
//
////	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Last Name")
//	@Column(name ="military_lastname")
//	private String lastname;
//
////	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid FathersName")
//	@Column(name ="military_fathersName")
//	private String fathersName;
//
////	@Pattern(regexp = "^[0-9\\s]{10}$", message = "Invalid NationalID")
//	@Column(name ="military_nationalID")
//	private String nationalID; // شماره ملی
//
////	@PastOrPresent(message = "Invalid birthDate ")
//	@Column(name ="military_birthdate")
//	private LocalDate birthdate;
//
////	@Pattern(regexp = "^[A-Za-zا-ی\\s]", message = "Invalid Exemption")
//	@Column(name = "military_exemption")
//	private MilitaryExemption exemption; //نوع معافیت
//
////	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid SerialNumber")
//	@Column(name = "military_serialNumber")
//	private String serialNumber;
//
////	@PastOrPresent(message = "Invalid issuance Date")
//	@Column(name = "military_issuanceDate")
//	private LocalDate issuanceDate;//تاریخ صدور
//
//
//	@Column(name = "military_vitiation")
//	private boolean militaryVitiation;//ابطال شده
//
////	@PastOrPresent(message = "Invalid vitiationDate Date")
//	@Column(name = "military_vitiationDate")
//	private LocalDate vitiationDate;//تاریخ ابطال
//
//	@Column(name = "military_vitiationStatus")
//	private String vitiationStatus;//دلیل ابطال
//
//	@Column(name = "military_deleted")
//	private boolean deleted;
//
//}