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

    private final PresenceAndAbsenceRepository presenceAndAbsenceRepository;

    public PresenceAndAbsenceServiceImpl(PresenceAndAbsenceRepository presenceAndAbsenceRepository) {
        this.presenceAndAbsenceRepository = presenceAndAbsenceRepository;
    }

    @Override
    public PresenceAndAbsence save(PresenceAndAbsence presenceAndAbsence){
        return presenceAndAbsenceRepository.save(presenceAndAbsence);
    }

    @Override
    public PresenceAndAbsence update(PresenceAndAbsence presenceAndAbsence) throws NoContentException {
        presenceAndAbsenceRepository.findById(presenceAndAbsence.getId()).orElseThrow(
                () -> new NoContentException("No PresenceAndAbsence Found with id : " + presenceAndAbsence.getId())
        );
        return presenceAndAbsenceRepository.save(presenceAndAbsence);
    }

    @Override
    public PresenceAndAbsence remove(Long id) throws NoContentException {
        PresenceAndAbsence presenceAndAbsence = presenceAndAbsenceRepository.findById(id).orElseThrow(
                () -> new NoContentException("No PresenceAndAbsence Found with id : " + id)
        );
        presenceAndAbsenceRepository.deleteById(id);
        return presenceAndAbsence;
    }

    @Override
    public PresenceAndAbsence logicalRemove(Long id) throws NoContentException {
        PresenceAndAbsence presenceAndAbsence = presenceAndAbsenceRepository.findById(id).orElseThrow(
                () -> new NoContentException("No PresenceAndAbsence Found with id : " + id)
        );
        presenceAndAbsenceRepository.logicalRemove(id);
        return presenceAndAbsence;
    }

    @Override
    public List<PresenceAndAbsence> findAll() {
        return presenceAndAbsenceRepository.findAll();
    }

    @Override
    public PresenceAndAbsence findById(Long id) throws NoContentException {
        return presenceAndAbsenceRepository.findById(id).orElseThrow(
                () -> new NoContentException("No PresenceAndAbsence Found with id : " + id)
        );
    }

    @Override
    public Long getPresenceAndAbsenceCount() {
        return presenceAndAbsenceRepository.count();
    }

    @Override
    public PresenceAndAbsence logicalRemoveWithReturn(Long id) throws NoContentException {
        Optional<PresenceAndAbsence> optionalPresenceAndAbsence = presenceAndAbsenceRepository.findPresenceAndAbsenceByIdAndDeletedFalse(id);

        if (optionalPresenceAndAbsence.isPresent()) {
            PresenceAndAbsence oldPresenceAndAbsence = optionalPresenceAndAbsence.get();
            oldPresenceAndAbsence.setDeleted(true);
            return presenceAndAbsenceRepository.save(oldPresenceAndAbsence);

        } else {
            throw new NoContentException("PresenceAndAbsence not found !");
        }
    }

    @Override
    public List<PresenceAndAbsence> findPresenceAndAbsenceByDeletedFalse() {
        return presenceAndAbsenceRepository.findPresenceAndAbsenceByDeletedFalse();
    }

    @Override
    public PresenceAndAbsence findPresenceAndAbsenceByIdAndDeletedFalse(Long id) throws NoContentException {
        return presenceAndAbsenceRepository.findPresenceAndAbsenceByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No PresenceAndAbsence Found with id : " + id)
        );
    }

    @Override
    public List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date) throws NoContentException{
        return presenceAndAbsenceRepository.findPresenceAndAbsenceByEmployeeIdAndDateAndDeletedFalse(id,date);
    }

    @Override
    public List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDeletedFalse(Long id) throws NoContentException {
        return presenceAndAbsenceRepository.findPresenceAndAbsenceByEmployeeIdAndDeletedFalse(id);
    }

    @Override
    public List<PresenceAndAbsence> findPresenceAndAbsenceByDateAndDeletedFalse(LocalDate date) throws NoContentException {
        return presenceAndAbsenceRepository.findPresenceAndAbsenceByDateAndDeletedFalse(date);
    }

    @Override
    public Long countByDeletedFalse() {
        return presenceAndAbsenceRepository.countByDeletedFalse();
    }
}
