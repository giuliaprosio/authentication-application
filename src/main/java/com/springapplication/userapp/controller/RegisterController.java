package com.springapplication.userapp.controller;

import com.springapplication.userapp.model.User;
import com.springapplication.userapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

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
        System.out.println(user.getUsername());

        userRepository.save(user);

        return "added";
    }




}
