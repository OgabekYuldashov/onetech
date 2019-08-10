package com.mum.onetech.controller;

import com.mum.onetech.domain.Seller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/seller")
@Controller
public class SellerController {

    @GetMapping("/")
    public String getSellerRegistrationForm(){
        return "SellerRegistrationForm";
    }


}
