package com.springapplication.userapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserappController {

    @GetMapping("/")
    public String welcome(Model model){
        model.addAttribute("message", "Welcome to our website!");
        return "index";
    }
}
