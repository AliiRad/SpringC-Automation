package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.PresenceAndAbsence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresenceAndAbsenceRepository extends JpaRepository<PresenceAndAbsence,Long> {
    @Modifying
    @Query("update presenceEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<PresenceAndAbsence> findPresenceAndAbsenceByDeletedFalse();

    Optional<PresenceAndAbsence> findPresenceAndAbsenceByIdAndDeletedFalse(Long id);

    List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDateAndDeletedFalse(Long id, LocalDate date);

    List<PresenceAndAbsence> findPresenceAndAbsenceByEmployeeIdAndDeletedFalse(Long id);
    List<PresenceAndAbsence> findPresenceAndAbsenceByDateAndDeletedFalse(LocalDate date);

    Long countByDeletedFalse();
}
