package com.mum.onetech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public String accessDenied(){

        return "403";
    }
}
