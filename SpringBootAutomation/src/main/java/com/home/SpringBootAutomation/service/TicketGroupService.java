package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.TicketGroup;

import java.util.List;

public interface TicketGroupService {
    TicketGroup save(TicketGroup ticketGroup);
    TicketGroup edit(TicketGroup ticketGroup);
    TicketGroup remove(TicketGroup ticketGroup);
    TicketGroup findById(Long id) throws NoContentException;
    List<TicketGroup> findAll();
    TicketGroup findByTitle(String title);
    List<TicketGroup> findByParentId(Long id);
    List<TicketGroup> findByParentRoot();

}
