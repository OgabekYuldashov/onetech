package com.mum.onetech.controller;

import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.Seller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//@RequestMapping("/seller")
@Controller
public class SellerController {

    @GetMapping("/")
    public String getSellerRegistrationForm(@ModelAttribute("seller") Seller seller) {
        return "SellerRegForm";
    }
    @PostMapping("/")
    public String processRegistrationForm(Seller seller , Model model){
        model.addAttribute("product", new Product());
        return "addProduct";
    }


}
