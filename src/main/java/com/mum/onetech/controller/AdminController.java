package com.mum.onetech.controller;

import com.mum.onetech.domain.Credentials;
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

    @GetMapping("/seller")
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
        //.....
        Credentials credentials1 =new Credentials();
        Credentials credentials2 =new Credentials();
        Credentials credentials3 =new Credentials();
        Credentials credentials4=new Credentials();
        credentials1.setFirstName("Zelalem");
        credentials2.setFirstName("Henok");
        credentials3.setFirstName("Oga'bek");
        credentials4.setFirstName("Abel");
        credentials1.setLastName("Zergaw");
        credentials2.setLastName("Melese");
        credentials3.setLastName("Yuldov");
        credentials4.setLastName("Tesfaye");
        credentials1.setEmail("zz@gmail.com");
        credentials2.setEmail("zz@yahoo.com");
        credentials3.setEmail("zz@hotmail.com");
        credentials4.setEmail("zz@hotoklikjmail.com");
        credentials1.setVerified(1);
        credentials2.setVerified(0);
        credentials3.setVerified(0);
        credentials4.setVerified(1);
        Seller seller1= new Seller();
        Seller seller2 = new Seller();
        Seller seller3= new Seller();
        Seller seller4 = new Seller();
        seller1.setCredentials(credentials1);
        seller2.setCredentials(credentials2);
        seller3.setCredentials(credentials3);
        seller4.setCredentials(credentials4);

        // credentials2.setVerified(true);
        seller1.setId(3l);
        seller2.setId(2l);
        seller4.setId(4l);
        seller3.setId(1l);
        sellersToReturn.add(seller1);
        sellersToReturn.add(seller2);
        sellersToReturn.add(seller3);
        sellersToReturn.add(seller4);

        //....



        model.addAttribute("sellerList", sellersToReturn);
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




