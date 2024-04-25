package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.AppointmentDecree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDecreeRepository extends JpaRepository<AppointmentDecree,Long> {
    @Modifying
    @Query("update appointmentDecreeEntity  oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<AppointmentDecree> findAppointmentDecreeByDeletedFalse();

    Optional<AppointmentDecree> findAppointmentDecreeByIdAndDeletedFalse(Long id);

    Long countByDeletedFalse();
}
