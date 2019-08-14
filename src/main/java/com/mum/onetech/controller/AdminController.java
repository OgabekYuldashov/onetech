package com.mum.onetech.controller;

import com.mum.onetech.domain.*;
import com.mum.onetech.repository.ReviewRepository;
import com.mum.onetech.service.ProductService;
import com.mum.onetech.service.ReviewService;
import com.mum.onetech.service.SellerService;
import com.mum.onetech.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {



    @Autowired
    ProductService productService;

    @Autowired
    SellerService sellerService;

    @Autowired
    ReviewService reviewService;
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

        seller.getCredentials().setVerified(1);

        return sellerService.save(seller);

    }

    @GetMapping("/advertised")
    public String getAdvertisedProductsPage(@RequestParam(value = "status", required = false) String status, Product product, Model model ) {
        List<Product> products = new ArrayList<>();

        if (status == null || status.equals("")) {
            products = productService.findAll();
        } else {
            if (status.equalsIgnoreCase("promoted")) {
                products = productService.findProductByStatusPromoted(PromoteType.PROMOTE);
            } else if (status.equalsIgnoreCase("Pending")) {
                products = productService.findProductByStatusNotPromoted(PromoteType.PENDING);
            }else {
                return "redirect:/advertised";
            }
        }

        model.addAttribute("productList", products);

        return "advertisement";
    }
    @RequestMapping(value="/acceptPromotion/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public Product verifyAdvertisement(@PathVariable("pid") String aid){

//        if(!Util.isPositiveInteger(pid))
//            return null;

        Product product = productService.findById(Long.valueOf(aid));
        if(product == null) return null;

       product.setPromote(PromoteType.PENDING);
        return productService.save(product);

    }

    @GetMapping("/reviews")
    public String getReviewsPage(@RequestParam(value = "status", required = false) String status, Seller seller, Model model) {
        List<Review> reviews = new ArrayList<>();

        if (status == null || status.equals("")) {
            reviews = reviewService.findAll();
        } else {
            if (status.equalsIgnoreCase("promote")) {
                reviews = reviewService.findReviewByStatusPending(ReviewStatus.APPROVED);
            } else if (status.equalsIgnoreCase("pending")) {
                reviews = reviewService.findReviewByStatusPending(ReviewStatus.PENDING);
            }else {
                return "redirect:/reviews";
            }
        }

        model.addAttribute("reviewList", reviews);

        return "reviewList";
    }
//    @RequestMapping(value="/acceptPromotion/{pid}",method = RequestMethod.POST)
//    @ResponseBody
//    public Review reviewApproval(@PathVariable("pid") String aid){
//
////        if(!Util.isPositiveInteger(pid))
////            return null;
//
//        Review review = reviewService.find(Long.valueOf(aid));
//        if(review == null) return null;
//
//        //review.setPromote(PromoteType.PENDING);
//        review.
//        return productService.save(review);
//
//    }
}




