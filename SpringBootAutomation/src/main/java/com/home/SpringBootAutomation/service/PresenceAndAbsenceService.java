package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.PresenceAndAbsence;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PresenceAndAbsenceService {
    PresenceAndAbsence save(PresenceAndAbsence presenceAndAbsence);
    PresenceAndAbsence update(PresenceAndAbsence presenceAndAbsence) throws NoContentException;
    PresenceAndAbsence remove(Long id) throws NoContentException;

    @Transactional
    PresenceAndAbsence logicalRemove(Long id) throws NoContentException;

    List<PresenceAndAbsence> findAll();
    PresenceAndAbsence findById(Long id) throws NoContentException;
    Long getPresenceAndAbsenceCount();

    PresenceAndAbsence logicalRemoveWithReturn(Long id) throws NoContentException;

    List<PresenceAndAbsence> findPresenceAndAbsenceByDeletedFalse();
    PresenceAndAbsence findPresenceAndAbsenceByIdAndDeletedFalse(Long id) throws NoContentException;

    List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date) throws NoContentException;
    List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDeletedFalse(Long id) throws NoContentException;
    List<PresenceAndAbsence> findPresenceAndAbsenceByDateAndDeletedFalse(LocalDate date) throws NoContentException;

    Long countByDeletedFalse();
}
