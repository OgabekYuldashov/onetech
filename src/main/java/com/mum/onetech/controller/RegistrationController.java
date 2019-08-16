package com.mum.onetech.controller;

import com.mum.onetech.domain.Buyer;
import com.mum.onetech.domain.Role;
import com.mum.onetech.domain.RoleType;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.service.BuyerService;
import com.mum.onetech.service.CredentialsService;
import com.mum.onetech.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    CredentialsService credentialsService;

    @GetMapping("/register")
    public String getSellerRegistrationForm(Model model) {
        model.addAttribute("seller", new Seller());
        model.addAttribute("buyer", new Buyer());

        model.addAttribute("activeTab", "buyer");

        return "register";
    }

    @PostMapping("/register/buyer")
    public String registerBuyer(@Valid @ModelAttribute("buyer") Buyer buyer, BindingResult bindingResult, Model model){
        model.addAttribute("seller", new Seller());

        model.addAttribute("activeTab", "buyer");

        if(bindingResult.hasErrors()){
            return "register";
        }

        if(credentialsService.isEmailInUse(buyer.getCredentials().getEmail())){
            model.addAttribute("buyerEmailInUse", true);
            return "register";
        }

        buyer.getCredentials().setRole(new Role(RoleType.BUYER));
        buyer.getCredentials().setVerified(1);
        buyerService.register(buyer);

        return "redirect:/login";
    }

    @PostMapping("/register/seller")
    public String registerSeller( @Valid @ModelAttribute("seller") Seller seller, BindingResult bindingResult, Model model){
        model.addAttribute("buyer", new Buyer());

        model.addAttribute("activeTab", "seller");

        if(bindingResult.hasErrors()){
            return "register";
        }

        if(credentialsService.isEmailInUse(seller.getCredentials().getEmail())){
            model.addAttribute("sellerEmailInUse", true);
            return "register";
        }

        Role role =new Role();
        role.setRole(RoleType.SELLER);
        seller.getCredentials().setRole(role);
        seller.getCredentials().setVerified(1);
        sellerService.register(seller);

        return "redirect:/seller";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String welcome(){
        return "welcome";
    }


}
