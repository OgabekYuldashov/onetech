package com.mum.onetech.controller;

import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.Role;
import com.mum.onetech.domain.RoleType;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping("/seller")
    public String processRegistrationForm(Seller seller , Model model){
        Role role =new Role(RoleType.SELLER);
        seller.getCredentials().setRole(role);
        sellerService.registerSeller(seller);
        System.out.println("seller" + seller);
        model.addAttribute("product", new Product());
        return "product";
    }



}
