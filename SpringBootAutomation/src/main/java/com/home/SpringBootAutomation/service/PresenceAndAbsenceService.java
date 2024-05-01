package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.PresenceAndAbsence;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PresenceAndAbsenceService {
    void save(PresenceAndAbsence presenceAndAbsence) throws Exception;
    void update(PresenceAndAbsence presenceAndAbsence) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<PresenceAndAbsence> findAll() throws Exception;
    Optional<PresenceAndAbsence> findById(Long id) throws NoContentException;
    Long getPresenceAndAbsenceCount();

    PresenceAndAbsence logicalRemoveWithReturn(Long id) throws NoContentException;

    List<PresenceAndAbsence> findPresenceAndAbsenceByDeletedFalse() throws Exception;
    Optional<PresenceAndAbsence> findPresenceAndAbsenceByIdAndDeletedFalse(Long id) throws NoContentException;

    List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date) throws NoContentException;
    List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDeletedFalse(Long id) throws NoContentException;
    List<PresenceAndAbsence> findPresenceAndAbsenceByDateAndDeletedFalse(LocalDate date) throws NoContentException;

    Long countByDeletedFalse();
}
