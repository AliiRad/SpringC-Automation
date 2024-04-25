package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.FileFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity(name = "AttachmentEntity")
@Table(name = "attachment_tbl")
public class Attachment {
    @Id
    @SequenceGenerator(name = "AttachmentSequence" , sequenceName = "attachment_seq",  allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "AttachmentSequence")
    private Long id;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FileFormat fileFormat;
    @Column(nullable = false)
    private byte[] content;
}
