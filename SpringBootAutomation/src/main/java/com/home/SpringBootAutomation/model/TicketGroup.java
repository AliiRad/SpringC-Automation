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

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "ticketGroupEntity")
@Table(name = "ticket_group_tbl")
public class TicketGroup {

    @Id
    @SequenceGenerator(name = "ticketGroupSeq", sequenceName = "ticket_group_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketGroupSeq")
    @Column(name = "ticket_group_id")
    private Long id;

    @Column(name = "ticket_group_title")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Title")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST )
    @JoinColumn(name = "ticket_group_parent_id")
    private TicketGroup parent;


//    @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//    private List<TicketGroup> ticketGroupList;

}
