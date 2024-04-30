package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Contact;
import com.home.SpringBootAutomation.model.Person;
import com.home.SpringBootAutomation.service.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Person Save Failed ! --->" + result.getAllErrors());
                return "contact";
            }
            service.save(contact);
            log.info("Controller - Contact Saved - Post Method --->" + contact.toString());

            model.addAttribute("message Type", "success");
            model.addAttribute("messageContent", "Contact saved successfully.");
            return "redirect:/contact";

        } catch (Exception e){
            log.error(e.getMessage());
            model.addAttribute("message Type", "error");
            model.addAttribute("message Content", e.getMessage());
            return "redirect:/contact";
        }
    }

    @PutMapping("/edit")
    public String edit(@Valid Contact contact, BindingResult result, Model model){
        try {
            if(result.hasErrors()){
                model.addAttribute("messageType", "error");
                model.addAttribute("messageContent", result.getAllErrors().toString());
                log.error("Controller - Contact Edit Failed ! --->" + result.getAllErrors());
                return "contact";
            }

            service.update(contact);
            log.info("Controller - Contact edited - put Method --->" + " contact : " + contact.toString());


            model.addAttribute("Contact", contact);
            model.addAttribute("message Type", "success");
            model.addAttribute("message content", "Contact edited Successfully.");
            return "redirect:/contact";

        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("message Type", "error");
            model.addAttribute("message Content", e.getMessage());
            return "redirect:/contact";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            service.logicalRemove(id);

            log.info("The Contact with Id :" + id + "Successfully Deleted");
            model.addAttribute("message Type", "success");
            model.addAttribute("message Content", "The Contact with Id :" + id + "Successfully Deleted");
            return "redirect:/contact";

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

                List<Contact> contactList = service.findContactByDeletedFalse();
                log.info("Controller - Find Contact with Deleted False - Get Method");

                model.addAttribute("contact", new Contact());
                model.addAttribute("contactList", contactList);
                model.addAttribute("message Type", "success");
                model.addAttribute("messageContent", "Contact List Is Not Empty");
                return "contact";

        }catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return "error-page";
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Contact>> findById(@PathVariable("id") Long id, Model model){
        try{
            Optional<Contact> contact = service.findContactByIdAndDeletedFalse(id);

            return ResponseEntity.ok(contact);

        } catch (Exception e) {

            log.error(e.getMessage());
            model.addAttribute("messageType", "error");
            model.addAttribute("messageContent", e.getMessage());

            return null;
        }
        }
    }

