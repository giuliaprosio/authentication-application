package com.springapplication.userapp.controllers;

import com.springapplication.userapp.User;
import com.springapplication.userapp.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/register")
    public String register(Model model) throws  Exception {
        model.addAttribute("user", new User());
        return "register";
    }

    /** TODO: Handle POST requests to add new users **/
    @PostMapping("/register")
    public String submission(@ModelAttribute User user, Model model) {

        model.addAttribute("user", user);

        System.out.println(user.getName());

        return "added";
    }


}
