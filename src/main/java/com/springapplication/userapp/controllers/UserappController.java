package com.springapplication.userapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserappController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
