package com.mum.onetech.controller;

import com.mum.onetech.domain.*;
import com.mum.onetech.jsonmodel.*;
import com.mum.onetech.service.BuyerService;
import com.mum.onetech.service.OrderService;
import com.mum.onetech.service.ProductService;
import com.mum.onetech.service.SellerService;
import com.mum.onetech.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;

@RequestMapping("/buyer")
@Controller
public class BuyerController {
    @Autowired
    ProductService productService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    OrderService orderService;

    @Autowired
    SellerService sellerService;


    @GetMapping("/cart")
    public String getCart(Model model, Authentication authentication){

        if(authentication == null) return "redirect:/error/access-denied";

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        if(buyer == null || buyer.getCredentials().getRole().getRole() != RoleType.BUYER) return "redirect:/error/access-denied";


        model.addAttribute("userCart", buyer.getShoppingCart());
        return "cart";
    }


    @GetMapping("/orders")
    public String getOrderHistory(Model model, Authentication authentication){

        if(authentication == null) return "redirect:/error/access-denied";

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        if(buyer == null || buyer.getCredentials().getRole().getRole() != RoleType.BUYER) return "redirect:/error/access-denied";


        model.addAttribute("userCart", buyer.getShoppingCart());
        return "cart";
    }


    @PostMapping("/cartdetails")
    @ResponseBody
//    @IsBuyer
    public CartModel addToCart(Authentication authentication){

        if(authentication == null) return null;

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        if(buyer == null || buyer.getCredentials().getRole().getRole() != RoleType.BUYER) return null;

        return new CartModel(buyer.getShoppingCart().getCartItems().size(), buyer.getShoppingCart().getTotalAmount());
    }


    @PostMapping("/cart/add")
    @ResponseBody
//    @IsBuyer
    public CartModel addToCart(@Valid @RequestBody CartItemModel cartItemModel, Authentication authentication){

        CartModel cartModel = new CartModel();
        cartModel.getGenericRes().setRespStatus(GenericRespStatus.FAILED);

        if(authentication == null){
            cartModel.getGenericRes().setMessage("Please login before adding to cart");
            return cartModel;
        }

        Product p = productService.getOneProductById(cartItemModel.getPid());
        if(p == null){
            cartModel.getGenericRes().setMessage("Product does not exist! Try again.");
            return cartModel;
        }

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        buyer.getShoppingCart().addIncreaseProduct(p, cartItemModel.getQuantity());
        buyer = buyerService.save(buyer);

        cartModel.getGenericRes().setRespStatus(GenericRespStatus.SUCCESS);
        cartModel.setItemCount(buyer.getShoppingCart().getCartItems().size());
        cartModel.setTotalAmount(buyer.getShoppingCart().getTotalAmount());

        return cartModel;
    }

    @PostMapping("/place_order")
    @ResponseBody
//    @IsBuyer
    public GenericJsonRespModel placeOrder(Authentication authentication){

        GenericJsonRespModel respModel = new GenericJsonRespModel();
        respModel.setRespStatus(GenericRespStatus.FAILED);

        if(authentication == null){
            respModel.setNextUrl("/error/access-denied");
            return respModel;
        }

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        if(buyer == null){
            respModel.setNextUrl("/error/access-denied");
            return respModel;
        }

        BuyerOrder buyerOrder = new BuyerOrder(buyer);
        buyerOrder.setCreateDate(Date.valueOf(LocalDate.now()));
        for(CartItem item : buyer.getShoppingCart().getCartItems()){
            buyerOrder.addOrderItem(new OrderItem(item.getQuantity(), item.getUnitPrice(), item.getProduct(), buyerOrder));
        }

        //***************
        buyerOrder.setPaymentMethod(PaymentMethod.CARD);

        buyer.addOrder(buyerOrder);
        orderService.save(buyerOrder);

        buyer.getShoppingCart().emptyCart();
        buyer = buyerService.save(buyer);

        respModel.setRespStatus(GenericRespStatus.SUCCESS);
        respModel.setNextUrl("/order_success");
        return respModel;
    }

    @PostMapping("/addreview/{pid}")
    @ResponseBody
//    @IsBuyer
    public GenericJsonRespModel addReview(@RequestBody @Valid ReviewModel reviewModel, @PathVariable("pid") String pid, Authentication authentication){
        GenericJsonRespModel respModel = new GenericJsonRespModel();
        respModel.setRespStatus(GenericRespStatus.FAILED);

        if(authentication == null){
            respModel.setMessage("You must be logged in to post a review");
            return respModel;
        }

        if(!Util.isPositiveInteger(pid)){
            respModel.setMessage("Bad request");
            return respModel;
        }

        Product p = productService.getOneProductById(Long.valueOf(pid));
        if(p == null){
            respModel.setMessage("Bad request");
            return respModel;
        }

        Buyer buyer = buyerService.findByEmail(authentication.getName());

        Review newReview = new Review(reviewModel.getTitle(), reviewModel.getMessage(), Date.valueOf(LocalDate.now()), p, buyer);
        buyer.addReview(newReview);

        buyer = buyerService.save(buyer);

        respModel.setRespStatus(GenericRespStatus.SUCCESS);
        respModel.setMessage("Review accepted. You'll be notified by email when it is approved");
        return respModel;
    }

    @PostMapping("/follow/{sid}")
    @ResponseBody
//    @IsBuyer
    public GenericJsonRespModel followSeller(@PathVariable("sid") String sid, Authentication authentication){
        GenericJsonRespModel respModel = new GenericJsonRespModel();
        respModel.setRespStatus(GenericRespStatus.FAILED);

        if(authentication == null){
            respModel.setMessage("You must be logged in to follow");
            return respModel;
        }

        if(!Util.isPositiveInteger(sid)){
            respModel.setMessage("Bad request");
            return respModel;
        }

        Seller seller = sellerService.findById(Long.valueOf(sid));
        if(seller == null){
            respModel.setMessage("Bad request");
            return respModel;
        }

        Buyer buyer = buyerService.findByEmail(authentication.getName());

        buyer.addSellerToFollow(seller);
        buyer = buyerService.save(buyer);

        respModel.setRespStatus(GenericRespStatus.SUCCESS);
        respModel.setMessage("Following");
        return respModel;
    }

    @PostMapping("/removecartitem/{itemId}")
    @ResponseBody
//    @IsBuyer
    public GenericJsonRespModel removeCartItem(@PathVariable("itemId") String itemId, Authentication authentication){
        GenericJsonRespModel respModel = new GenericJsonRespModel();
        respModel.setRespStatus(GenericRespStatus.FAILED);

        if(authentication == null){
            respModel.setMessage("You must be logged in to remove");
            return respModel;
        }

        if(!Util.isPositiveInteger(itemId)){
            respModel.setMessage("Bad request");
            return respModel;
        }

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        if (!buyer.getShoppingCart().containsOrderItem(Long.valueOf(itemId))) {
            respModel.setMessage("Bad request");
            return respModel;
        }

        //Remove CartItem from the cart
        buyer.getShoppingCart().removeCartItemById(Long.valueOf(itemId));

        buyer = buyerService.save(buyer);

        respModel.setRespStatus(GenericRespStatus.SUCCESS);
        respModel.setMessage("Removed");
        respModel.getDataMap().put("totalAmount", buyer.getShoppingCart().getTotalAmount());
        return respModel;
    }

}
