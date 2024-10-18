package com.springapplication.userapp.controller;

import com.springapplication.userapp.func.Either;
import com.springapplication.userapp.model.User;
import com.springapplication.userapp.model.UserError;
import com.springapplication.userapp.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

    private final UserDetailsServiceImpl userDetailsService;

    public RegisterController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    @ResponseBody
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String submission(@RequestBody User user) {

        Either<UserError, User> result = userDetailsService.registerUser(user);

        if(result.isLeft()) {
            return result.left().toString();
        }
        return "added";
    }

}
