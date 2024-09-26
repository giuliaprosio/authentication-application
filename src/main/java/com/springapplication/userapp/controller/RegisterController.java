package com.springapplication.userapp.controller;

import com.springapplication.userapp.error.ApiErrorResponse;
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
        Either<UserError, User> result = userDetailsService.registerUser(user);

        if(result.isLeft()) {
            model.addAttribute("errorMessage", result.left().toString());
            model.addAttribute("user", user);
            return "register";
        }

        model.addAttribute("user", result.right());
        return "added";

        /*return result
                .mapLeft( e -> model.addAttribute("errorMessage", e.toString()))
                .fold(
                userError -> {
                    model.addAttribute("user", user);
                    return "register";
                },
                userSuccess -> {
                    model.addAttribute("user", userSuccess);
                    return "added";
                }
        );*/
    }

}
