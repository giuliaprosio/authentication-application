package com.springapplication.userapp.controller;

import com.springapplication.userapp.model.Result;
import com.springapplication.userapp.model.User;
import com.springapplication.userapp.model.UserError;
import com.springapplication.userapp.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

    /*@Autowired
    private UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public RegisterController (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        //this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }*/

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
        Result<User, UserError> result = userDetailsService.registerUser(user);

        return result.fold(
                userSuccess -> {
                    model.addAttribute("user", userSuccess);
                    return "added";
                },
                userError -> {
                    if(userError instanceof UserError.DuplicatedUsername) {
                        model.addAttribute("errorMessage", "Username is already taken.");
                    }else if(userError instanceof UserError.NoUsername) {
                        model.addAttribute("errorMessage", "Username cannot be empty");
                    }
                    model.addAttribute("user", user);
                    return "register";
                }
        );
    }

    /*@PostMapping("/register")
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
    }*/




}
