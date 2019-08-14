package com.mum.onetech.controller;

import com.mum.onetech.domain.*;
import com.mum.onetech.jsonmodel.CartItemModel;
import com.mum.onetech.jsonmodel.CartModel;
import com.mum.onetech.jsonmodel.GenericJsonRespModel;
import com.mum.onetech.jsonmodel.GenericRespStatus;
import com.mum.onetech.service.BuyerService;
import com.mum.onetech.service.OrderService;
import com.mum.onetech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;

@RequestMapping("/buyer")
@Controller
public class BuyerController {
    @Autowired
    ProductService productService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    OrderService orderService;

    @PostMapping("/cartdetails")
    @ResponseBody
//    @IsBuyer
    public CartModel addToCart(Authentication authentication){

        if(authentication == null) return null;

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        if(buyer == null || buyer.getCredentials().getRole().getRole() != RoleType.BUYER) return null;

        return new CartModel(buyer.getShoppingCart().getCartItems().size(), buyer.getShoppingCart().getTotalAmount());
    }


    @GetMapping("/cart")
    public String getCart(Model model, Authentication authentication){

        if(authentication == null) return "redirect:/error/access-denied";

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        if(buyer == null || buyer.getCredentials().getRole().getRole() != RoleType.BUYER) return "redirect:/error/access-denied";


        model.addAttribute("userCart", buyer.getShoppingCart());
        return "cart";
    }

    @PostMapping("/cart/add")
    @ResponseBody
//    @IsBuyer
    public CartModel addToCart(@Valid @RequestBody CartItemModel cartItemModel, Authentication authentication){

        if(authentication == null) return null;

        Product p = productService.getOneProductById(cartItemModel.getPid());
        if(p == null) return null;

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        buyer.getShoppingCart().addIncreaseProduct(p, cartItemModel.getQuantity());
        buyer = buyerService.save(buyer);

        return new CartModel(buyer.getShoppingCart().getCartItems().size(), buyer.getShoppingCart().getTotalAmount());
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
}
