package com.springapplication.userapp.controllers;

import antlr.StringUtils;
import com.springapplication.userapp.User;
import com.springapplication.userapp.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public RegisterController (PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/login")
    public String login() throws Exception {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) throws  Exception {
        model.addAttribute("user", new User());
        return "register";
    }

    /** TODO: Handle POST requests to add new users **/

    @PostMapping("/register")
    public String submission(@ModelAttribute User user, Model model) {

        if(!user.getPassword().equals("")){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        model.addAttribute("user", user);


        userRepository.save(user);

        return "added";
    }


}
