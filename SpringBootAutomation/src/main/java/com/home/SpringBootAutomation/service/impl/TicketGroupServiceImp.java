package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.TicketGroup;
import com.home.SpringBootAutomation.repository.TicketGroupRepository;
import com.home.SpringBootAutomation.service.TicketGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketGroupServiceImp implements TicketGroupService {
    private TicketGroupRepository ticketGroupRepository;

    public TicketGroupServiceImp(TicketGroupRepository ticketGroupRepository) {
        this.ticketGroupRepository = ticketGroupRepository;
    }

    @Override
    public TicketGroup save(TicketGroup ticketGroup) {
        ticketGroupRepository.save(ticketGroup);
        return ticketGroup;
    }

    @Override
    public TicketGroup edit(TicketGroup ticketGroup) {
        ticketGroupRepository.save(ticketGroup);
        return ticketGroup;
    }

    @Override
    public TicketGroup remove(TicketGroup ticketGroup) {
        ticketGroupRepository.delete(ticketGroup);
        return ticketGroup;
    }

    @Override
    public TicketGroup findById(Long id) {
        Optional<TicketGroup> group = ticketGroupRepository.findById(id);
        return (group.isPresent()) ? group.get() : null;

    }

    @Override
    public List<TicketGroup> findAll() {
        List<TicketGroup> ticketGroupList = ticketGroupRepository.findAll();
        return ticketGroupList;
    }

    @Override
    public TicketGroup findByTitle(String title) {
        Optional<TicketGroup> group = Optional.ofNullable(ticketGroupRepository.findByTitle(title));
        return (group.isPresent()) ? group.get() : null;
    }

    @Override
    public List<TicketGroup> findByParentId(Long id) {
        List<TicketGroup> ticketGroupList = ticketGroupRepository.findByParentId(id);
        return ticketGroupList;
    }

    @Override
    public List<TicketGroup> findByParentRoot() {
        List<TicketGroup> ticketGroupList = ticketGroupRepository.findByParentRoot();
        return ticketGroupList;
    }
}
