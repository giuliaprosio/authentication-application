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

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) throws Exception {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String successfulLogin(@ModelAttribute User user, Model model) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) throws  Exception {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String submission(@ModelAttribute User user, Model model) {

        if(!user.getPassword().equals("") && !user.getSecondPassword().equals("")){
            if(!user.getPassword().equals(user.getSecondPassword())) {
                return "redirect:register";
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        }
        model.addAttribute("user", user);


        userRepository.save(user);

        return "added";
    }


}
