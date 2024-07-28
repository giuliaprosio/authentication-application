package com.springapplication.userapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserappController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    /* TODO: Set up login of a user similarly to submission:
    *   HTML form
    *   connect to mysql database
    *   match username and password
    *   if match, connect - else show not authorized*/

    /* TODO: encrypt stored password and not show password client-side
    * */

}
