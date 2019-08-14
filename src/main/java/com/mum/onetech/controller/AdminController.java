package com.mum.onetech.controller;

import com.mum.onetech.domain.Seller;
import com.mum.onetech.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    SellerService sellerService;

    @GetMapping("/adminForm")
    public String LoginPage() {
        return "adminForm";
    }


    /*//////////////////////////////////*/

    @GetMapping("/sellers")
    public String listSellersInformation(@RequestParam(value = "status", required = false) String status, Seller seller, Model model) {

        List<Seller> sellersToReturn = new ArrayList<>();

        if(status == null || status.equals("")){
            sellersToReturn = sellerService.findAllSellers();
        }else {
            if(status.equalsIgnoreCase("verified")){
                sellersToReturn = sellerService.getVerifiedSellers();
            }else if(status.equalsIgnoreCase("unverified")){
                sellersToReturn = sellerService.getUnverifiedSellers();
            }
        }

        model.addAttribute("sellers", sellersToReturn);
        return "sellers";
    }

    @GetMapping("/advertised")
    public String getAdvertisedProductsPage() {

        return "advertised_products";
    }

    @GetMapping("/reviews")
    public String getReviewsPage() {

        return "reviews";
    }

}




