package com.home.SpringBootAutomation.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "groupEntity")
@Table(name = "group_tbl")
public class TicketGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "g_title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "g_parent_id")
    private TicketGroup parent;

    @OneToMany(mappedBy = "parent")
    private List<TicketGroup> ticketGroupList;

}
