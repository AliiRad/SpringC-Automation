package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.exceptions.NoContentException;
import com.home.SpringBootAutomation.model.PresenceAndAbsence;
import com.home.SpringBootAutomation.repository.PresenceAndAbsenceRepository;
import com.home.SpringBootAutomation.service.PresenceAndAbsenceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PresenceAndAbsenceServiceImpl implements PresenceAndAbsenceService {

    private final PresenceAndAbsenceRepository repository;

    public PresenceAndAbsenceServiceImpl(PresenceAndAbsenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(PresenceAndAbsence presenceAndAbsence) throws Exception {
        repository.save(presenceAndAbsence);
    }

    @Override
    public void update(PresenceAndAbsence presenceAndAbsence) throws NoContentException {
        Optional<PresenceAndAbsence> optionalPresenceAndAbsence = repository.findPresenceAndAbsenceByIdAndDeletedFalse(presenceAndAbsence.getId());

        if (optionalPresenceAndAbsence.isPresent()) {
            repository.save(presenceAndAbsence);
        } else {
            throw new NoContentException("PresenceAndAbsence not found !");
        }
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        Optional<PresenceAndAbsence> optionalPresenceAndAbsence = repository.findPresenceAndAbsenceByIdAndDeletedFalse(id);
        if (optionalPresenceAndAbsence.isPresent()) {
            repository.logicalRemove(id);
        } else {
            throw new NoContentException("PresenceAndAbsence not found !");
        }
    }

    @Override
    public List<PresenceAndAbsence> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Optional<PresenceAndAbsence> findById(Long id) throws NoContentException {
        Optional<PresenceAndAbsence> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("PresenceAndAbsence not found !");
        }
    }

    @Override
    public Long getPresenceAndAbsenceCount() {
        return repository.count();
    }

    @Override
    public PresenceAndAbsence logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<PresenceAndAbsence> optionalPresenceAndAbsence = repository.findPresenceAndAbsenceByIdAndDeletedFalse(id);

        if (optionalPresenceAndAbsence.isPresent()) {
            PresenceAndAbsence oldPresenceAndAbsence = optionalPresenceAndAbsence.get();
            oldPresenceAndAbsence.setDeleted(true);
            return repository.save(oldPresenceAndAbsence);

        } else {
            throw new NoContentException("PresenceAndAbsence not found !");
        }
    }

    @Override
    public List<PresenceAndAbsence> findPresenceAndAbsenceByDeletedFalse() throws Exception {
        return repository.findPresenceAndAbsenceByDeletedFalse();
    }

    @Override
    public Optional<PresenceAndAbsence> findPresenceAndAbsenceByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<PresenceAndAbsence> optional = repository.findPresenceAndAbsenceByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("PresenceAndAbsence not found !");
        }
    }

    @Override
    public List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date) throws NoContentException {
        return repository.findPresenceAndAbsenceByEmployeeIdAndDateAndDeletedFalse(id,date);
    }

    @Override
    public List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDeletedFalse(Long id) throws NoContentException {
        return repository.findPresenceAndAbsenceByEmployeeIdAndDeletedFalse(id);
    }

    @Override
    public List<PresenceAndAbsence> findPresenceAndAbsenceByDateAndDeletedFalse(LocalDate date) throws NoContentException {
        return repository.findPresenceAndAbsenceByDateAndDeletedFalse(date);
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
