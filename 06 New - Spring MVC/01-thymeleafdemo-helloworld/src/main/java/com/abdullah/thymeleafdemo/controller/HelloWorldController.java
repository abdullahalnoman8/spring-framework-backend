package com.abdullah.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
    // nedd a controller method to show initial HTML form

    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // need controller method to process the HTML form

    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    // need a controller method to read form data

    @RequestMapping("/processFormVersionTwo")
    public String letsShouteDude(HttpServletRequest request, Model model) {

        // read the request parameter from  the html form
        String theName = request.getParameter("studentName");
        // convert the data to all caps
        theName = theName.toUpperCase();
        // create the message
        String result = "Yo ! " + theName;
        // add message to the model
        model.addAttribute("message", result);
        return "helloworld";
    }
}
