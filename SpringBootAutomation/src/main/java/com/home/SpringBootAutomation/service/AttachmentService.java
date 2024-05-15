package com.home.SpringBootAutomation.service;

import com.home.SpringBootAutomation.model.Attachment;


public interface AttachmentService {
    Attachment save(Attachment attachment);
    Attachment edit(Attachment attachment);
    Attachment remove(String fileName);
    Attachment findById(Long id);
    Attachment findByFileName(String fileName);

}
