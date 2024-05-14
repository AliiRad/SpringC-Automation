package com.home.SpringBootAutomation.repository;

import com.home.SpringBootAutomation.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
    Optional<Attachment> findByFileName(String fileName);
}
