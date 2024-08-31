package com.springapplication.userapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReturnController {

    @GetMapping("/returnPage")
    public String returnPage() throws Exception {
        return "added";
    }

}
