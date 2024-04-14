package com.home.SpringBootAutomation.Model;

import com.home.SpringBootAutomation.Enum.MilitaryExemption;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Table(name = "Military_tbl")
@Entity(name = "MilitaryEntity")


public class MilitaryServiceModel {

	//پایان خدمت سربازی

	@Id
	@SequenceGenerator(name = "militarySeq", sequenceName = "military_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "militarySeq")
	@Column(name = "military_id",length = 20, nullable = false)
	private long id;

//	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//	private PersonModel personModel;//چطوری و در کجا شرط بگذاریم که اگر زن باشد نیاز به پایان خدمت ندارد؟؟

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Name")
	@Column(name ="military_name" ,length =20 )
	private String name;

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid Last Name")
	@Column(name ="military_lastname" ,length =20 )
	private String lastname;

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid FathersName")
	@Column(name ="military_fathersName" ,length =20 )
	private String fathersName;

	@Pattern(regexp = "^[0-9\\s]{10}$", message = "Invalid NationalID")
	@Column(name ="military_nationalID" ,length =10 )
	private String nationalID; // شماره ملی

	@PastOrPresent(message = "Invalid birthDate ")
	@Column(name ="military_birthdate")
	private LocalDate birthdate;

	@Pattern(regexp = "^[A-Za-zا-ی\\s]", message = "Invalid Exemption")
	@Column(name = "military_exemption",length = 20)
	private MilitaryExemption exemption; //نوع معافیت

	@Pattern(regexp = "^[A-Za-zا-ی\\s]{1,20}$", message = "Invalid SerialNumber")
	@Column(name = "military_serialNumber",length = 20)
	private String serialNumber;

	@PastOrPresent(message = "Invalid issuance Date")
	@Column(name = "military_issuanceDate")
	private LocalDateTime issuanceDate;//تاریخ صدور


	@Column(name = "military_vitiation",length = 3)
	private boolean militaryVitiation;//ابطال شده

	@PastOrPresent(message = "Invalid vitiationDate Date")
	@Column(name = "military_vitiationDate")
	private LocalDate vitiationDate;//تاریخ ابطال

	@Column(name = "military_vitiationStatus")
	private String vitiationStatus;//دلیل ابطال

	@Column(name = "military_deleted",length = 5)
	private boolean deleted;

}