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

import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "userEntity")
@Table(name = "user_tbl" )
public class User {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_user_name",  columnDefinition = "VARCHAR2(30)", unique = true)
    @Pattern(regexp = "^[a-zA-Z1-9\\s]{3,30}$", message = "Invalid Username")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    @NotBlank(message = "Should Not Be Null")
    private String username;

    @Column(name = "user_password", columnDefinition = "VARCHAR2(30)")
    @Pattern(regexp = "^[a-zA-Z1-9\\s]{4,30}$", message = "Invalid Password")
    @Size(min = 4, max = 30, message = "Password must be between 8 and 30 characters")
    @NotBlank(message = "Should Not Be Null")
    private String password;

    @Column(name = "user_status")
    private boolean status =true;

    @Column(name = "user_deleted")
    private boolean deleted;

    @OneToOne
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY , mappedBy = "user")
    private Set<Role> roleSet ;
}
