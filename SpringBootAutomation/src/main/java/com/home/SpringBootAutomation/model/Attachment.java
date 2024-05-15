package com.home.SpringBootAutomation.model;

import com.home.SpringBootAutomation.enums.FileFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "attachmentEntity")
@Table(name = "attachment_tbl")
public class Attachment {
    @Id
    @SequenceGenerator(name = "AttachmentSequence" , sequenceName = "attachment_seq",  allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "AttachmentSequence")
    @Column(name = "attachment_id")
    private Long id;

    @Column(name = "attachment_file_name", columnDefinition = "NVARCHAR2(50)")
    @NotBlank(message = "Should Not Be Null")
    private String fileName;

    @Column(name = "attachment_file_format")
    @Enumerated(EnumType.ORDINAL)
    private FileFormat fileFormat;

    @Column(name = "attachment_content")
    private byte[] content;

    @Column(name = "attachment_file_path")
    private String filePath;

    @Column(name = "attachment_file_caption", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Caption")
    @Size(min = 3, max = 50, message = "Caption must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String caption;

    @Column(name = "attachment_deleted")
    private boolean deleted;
}
