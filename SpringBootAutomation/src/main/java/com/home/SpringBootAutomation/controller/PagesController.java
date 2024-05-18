package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Attachment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {

    @RequestMapping("/attachment")
    public ModelAndView showAttachmentPage(ModelAndView modelAndView){
        modelAndView.addObject("attachment",new Attachment());
        modelAndView.setViewName("attachment/attachmentUpload");
        return modelAndView;
    }
}
