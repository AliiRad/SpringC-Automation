package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.service.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/contact")
@Slf4j
public class ContactController {

    private final ContactService service;

    @Autowired
    public ContactController(ContactService service) { this.service = service; }

    @PostMapping("/sava")
    public String save(@Valid Contact contact, BindingResult result, Model model){
        try {
            if (result.hasErrors()) {
                log.error(result.getAllErrors().toString());

                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                return "contact";
            }else {
                service.save(contact);
                log.info("Contact Saved - Post Method");
                log.info(contact.toString());

                model.addAttribute("message Type", "success");
                model.addAttribute("messageContent", "Contact saved successfully.");
                return "redirect:/contact";
            }
        } catch (Exception e){
            log.error(e.getMessage());
            model.addAttribute("message Type", "error");
            model.addAttribute("message Content", e.getMessage());
            return "redirect:/contact";
        }
    }

    @PutMapping("/edit")
    public String edit(Long id, @Valid Contact contact, BindingResult result, Model model){
        try {
            if(result.hasErrors()){
                throw new ValidationException(result.getAllErrors().toString());
            }

            service.update(contact);
            log.info("Contact edited - put Method");
            log.info(contact.toString());

            model.addAttribute("Contact", contact);
            model.addAttribute("message Type", "success");
            model.addAttribute("message content", "Contact edited Successfully.");
            return "redirect:/contact";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("message Type", "error");
            model.addAttribute("message Content", e.getMessage());
            return "contact";
        }
    }

    @DeleteMapping("/delete")
    public String delete(Long id, Model model) {
        try {
            if(service.findContactByIdAndDeletedFalse(id).isPresent()){
                service.logicalRemove(id);

                log.info("Contact Deleted - Put Method");
                log.info("The Contact with Id :" + id + "Successfully Deleted");
                model.addAttribute("message Type", "success");
                model.addAttribute("message Content", "The Contact with Id :" + id + "Successfully Deleted");
                return "redirect:/content";
            } else {
                model.addAttribute("message Type", "error");
                model.addAttribute("message Content", "The Contact with Id :" + id + "Successfully Deleted");
                return "redirect:/content";
            }
        }catch (Exception e){
            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("message Content", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping
    public String findAll(Model model) {
        try {
            if (service.countByDeletedFalse() > 0) {
                List<Contact> contactList = service.findContactByDeletedFalse();
                log.info("Find Contact with Deleted False - Get Method");

                model.addAttribute("contact", new Contact());
                model.addAttribute("contactList", contactList);
                model.addAttribute("message Type", "success");
                model.addAttribute("messageContent", "Contact List Is Not Empty");
                return "content";
            } else{
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", "Content List Is Empty");

            return "content";
            }
        }catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        try{
            Optional<Contact> contact = service.findContactByIdAndDeletedFalse(id);
            log.info("Find By Id And Deleted False - Get Method");

            if (contact.isPresent()) {
                log.info("Active Content With Id : " + id + " Was Founded");

                model.addAttribute("contact", contact);
                model.addAttribute("messageType", "success");
                model.addAttribute("messageContent", "Active Contact With Id : " + id + " Was Found");
                return "contact";
            } else {
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", "Active Contact With Id : " + id + " Was Not Found");
                return "contact";

            }

        } catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
        }
    }

