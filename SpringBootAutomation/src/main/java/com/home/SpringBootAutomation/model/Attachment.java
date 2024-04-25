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

@Entity(name = "AttachmentEntity")
@Table(name = "attachment_tbl")
public class Attachment {




    @Id
    @SequenceGenerator(name = "AttachmentSequence" , sequenceName = "attachment_seq",  allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "AttachmentSequence")
    @Column(name = "attachment_id", nullable = false)
    private Long id;
    @Column(name = "attachment_fileName", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @NotBlank(message = "Should Not Be Null")
    private String fileName;
    @Column(name = "attachment_fileFormat", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Should Not Be Null")
    private FileFormat fileFormat;
    @Column(name = "attachment_content", nullable = false)
    private byte[] content;
    @Column(name = "attachment_fileCaption", length = 50, nullable = false, columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Caption")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String caption;
}
