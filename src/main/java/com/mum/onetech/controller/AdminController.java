package com.mum.onetech.controller;

import com.mum.onetech.domain.Credentials;
import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.PromoteType;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.service.ProductService;
import com.mum.onetech.service.SellerService;
import com.mum.onetech.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
//    private static  final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    ProductService productService;
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

        if (status == null || status.equals("")) {
            sellersToReturn = sellerService.findAllSellers();
        } else {
            if (status.equalsIgnoreCase("verified")) {
//                return "redirect:/admin/sellers";
                sellersToReturn = sellerService.getVerifiedSellers();
            } else if (status.equalsIgnoreCase("unverified")) {
                sellersToReturn = sellerService.getUnverifiedSellers();
            }else {
                return "redirect:/sellers";
            }
        }

        model.addAttribute("sellerList", sellersToReturn);
        return "sellers";
    }
    @RequestMapping(value="/verifySeller/{sid}",method = RequestMethod.POST)
    @ResponseBody
    public Seller verifySeller(@PathVariable("sid") String sid){


        if(!Util.isPositiveInteger(sid)) return null;

        Seller seller = sellerService.findSellerById(Long.valueOf(sid));
        if(seller == null) return null;

        return sellerService.save(seller);

    }

    @GetMapping("/advertised")
    public String getAdvertisedProductsPage(@RequestParam(value = "status", required = false) String status, Product product, Model model ) {
        List<Product> products = new ArrayList<>();

        if (status == null || status.equals("")) {
           // products = productService.findProductByStatus(PromoteType);
        } else {
            if (status.equalsIgnoreCase("promoted")) {
                products = productService.get;
            } else if (status.equalsIgnoreCase("notpromoted")) {
                products = productService.get
            }else {
                return "redirect:/sellers";
            }
        }

        model.addAttribute("sellerList", sellersToReturn);
        return "sellers";
      model.addAttribute("newModel",model);
        return "advertisement";
    }

    @GetMapping("/reviews")
    public String getReviewsPage() {

        return "reviewList";
    }

}




