package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;
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

@Entity(name = "groupEntity")
@Table(name = "group_tbl")
public class TicketGroup {
    //TODO: GenerationType.SEQUENCE
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_title") //TODO: Put All Needed Validation Constraints .
    private String title;

    @ManyToOne
    @JoinColumn(name = "group_parent_id")
    private TicketGroup parent;

    @OneToMany(mappedBy = "parent")
    //TODO: cascade = CascadeType.ALL, fetch = FetchType.LAZY
    private List<TicketGroup> ticketGroupList;

}
