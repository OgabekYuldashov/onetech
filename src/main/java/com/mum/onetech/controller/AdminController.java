package com.mum.onetech.controller;

import com.mum.onetech.domain.Seller;
import com.mum.onetech.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
        @Autowired
        SellerService sellerService;

        @GetMapping("/adminForm")
        public String LoginPage()
        {
            return "adminForm";
        }

        @PostMapping("/sellers")
        public String listSellersInformation(Seller seller,Model model){
            model.addAttribute("sellers", sellerService.findAllSellers());
            List<Seller> sellerList= new ArrayList<>();


            return "table";
        }


    }




