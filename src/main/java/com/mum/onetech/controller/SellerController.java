package com.mum.onetech.controller;

import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.Role;
import com.mum.onetech.domain.RoleType;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.service.ProductService;
import com.mum.onetech.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ProductService productService;
    @ModelAttribute("products")
    public List<Product> getAllSellerProduct(Authentication authentication,Model model){
        if(authentication != null){
            System.out.println("****************"+authentication.getName());
            Seller seller=sellerService.findOneByEmail(authentication.getName());
            System.out.println("****************"+seller );
            model.addAttribute("seller",seller);
            return productService.findListOfProductBySeller(seller);
        }
        return productService.findAll();
    }

    @GetMapping("/seller")
    public String getSellerRegistrationForm(@ModelAttribute("seller") Seller seller) {

        return "productSideBarList";
    }

//    @PostMapping("/seller")
//    public String processRegistrationForm(Seller seller , Model model){
//        Role role =new Role();
//        role.setRole(RoleType.SELLER);
//        seller.getCredentials().setRole(role);
//        sellerService.registerSeller(seller);
//
//        model.addAttribute("product", new Product());
//        return "redirect:/product";
//
//    }



}
