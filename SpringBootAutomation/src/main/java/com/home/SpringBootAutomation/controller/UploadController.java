package com.home.SpringBootAutomation.controller;


import com.home.SpringBootAutomation.model.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/uploadFile")
@Slf4j
public class UploadController {

    private static final String UPLOAD_DIR = "C:/uploads"; // Change this to your desired directory

    @PostMapping("/upload")
    public String handleFileUpload(@ModelAttribute("attachment") Attachment attachment,
                                   RedirectAttributes redirectAttributes) {

        if (attachment == null || attachment.getContent() == null || attachment.getContent().length == 0) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/uploadStatus";
        }

        String fileName = attachment.getFileName();
        String fileType = attachment.getFileFormat().toString();

        // Check file type
        if (!isValidFileType(fileType)) {
            redirectAttributes.addFlashAttribute("message", "File type not allowed. Please upload only PDF, JPG, JPEG, or BMP files.");
            return "redirect:/uploadStatus";
        }

        try {
            // Specify the directory to save the file
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save the file
            File uploadedFile = new File(uploadDir, fileName);
            try (FileOutputStream fos = new FileOutputStream(uploadedFile)) {
                fos.write(attachment.getContent());
            }

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + fileName + "!");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + fileName + ": " + e.getMessage());
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    private boolean isValidFileType(String fileType) {
        return fileType != null && (fileType.equalsIgnoreCase("pdf") ||
                fileType.equalsIgnoreCase("jpg") ||
                fileType.equalsIgnoreCase("jpeg") ||
                fileType.equalsIgnoreCase("bmp"));
    }
}
