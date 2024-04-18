package com.home.SpringBootAutomation.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class AttachmentUploadController {

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("pdf", "jpg", "jpeg", "bmp");

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/uploadStatus";
        }

        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);

        if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
            redirectAttributes.addFlashAttribute("message",
                    "File type not allowed. Please upload only PDF, JPG, JPEG, or BMP files.");
            return "redirect:/uploadStatus";
        }

        try {
            // Specify the directory to save the file
            String uploadDir = "C:/uploads"; // Change this to your desired directory

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file
            File uploadedFile = new File(uploadDir, originalFilename);
            file.transferTo(uploadedFile);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + originalFilename + "!");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message",
                    "Failed to upload " + originalFilename + ": " + e.getMessage());
        }

        return "redirect:/uploadStatus";
    }
}
