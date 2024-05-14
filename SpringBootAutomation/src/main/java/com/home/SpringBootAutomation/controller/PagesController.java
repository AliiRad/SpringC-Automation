package com.home.SpringBootAutomation.controller;

import com.home.SpringBootAutomation.model.Attachment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {

    @RequestMapping("/index0")
    public String home0(){
        return "index0";
    }

    @RequestMapping("/index1")
    public String home(){
        return "index1";
    }

    @RequestMapping("/index2")
    public String home2(){
        return "index2";
    }

    @RequestMapping("/index3")
    public String home3(){
        return "index3";
    }

    @RequestMapping("/index4")
    public String home4(){
        return "index4";
    }

    @RequestMapping("/index5")
    public String home5(){
        return "index5";
    }


    @RequestMapping("/index6")
    public String home6(){
        return "index6";
    }

    @RequestMapping("/attachment")
    public ModelAndView showAttachmentPage(ModelAndView modelAndView){
        modelAndView.addObject("Attachment",new Attachment());
        modelAndView.setViewName("attachment/attachmentUpload");
        return modelAndView;
    }
}
