package com.springapplication.userapp.controllers;

import com.springapplication.userapp.User;
import com.springapplication.userapp.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/register")
    public String register() throws  Exception {
        return "register";
    }

    /** TODO: Handle POST requests to add new users **/
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userForm") User user, Map<String, Object> model) {
        User u = new User();
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        user.setPassword(user.getPassword());

        userRepository.save(user);
        System.out.println(user.getEmail());

        return "RegistrationSuccess";
    }


}
