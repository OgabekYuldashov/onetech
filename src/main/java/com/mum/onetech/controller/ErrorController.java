package com.mum.onetech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public String accessDenied(Model model){

        model.addAttribute("errTitle", "403 oops!");
        model.addAttribute("errMessage", "You need to authenticate to access this resource");
        return "error-custom";
    }

    @GetMapping("/login-err")
    public String loginError(){

        return "redirect:/login";
    }

    @GetMapping("/bad-request")
    public String badRequest(){

        return "redirect:/login";
    }


}
