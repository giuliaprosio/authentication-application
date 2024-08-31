package com.springapplication.userapp.controller;

import com.springapplication.userapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/redirect")
    public String redirect(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}
