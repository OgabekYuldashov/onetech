package com.mum.onetech.controller;

import com.mum.onetech.annotation.IsBuyer;
import com.mum.onetech.domain.Buyer;
import com.mum.onetech.domain.Cart;
import com.mum.onetech.domain.Product;
import com.mum.onetech.jsonmodel.CartItemModel;
import com.mum.onetech.jsonmodel.CartModel;
import com.mum.onetech.service.BuyerService;
import com.mum.onetech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@SessionAttributes("cartDetails")
public class OrderController {
    @Autowired
    ProductService productService;

    @Autowired
    BuyerService buyerService;


    @GetMapping("/listOrder")
    public String listOfOrder(){
        return "order";
    }


    @GetMapping("/order_success")
    public String orderSuccess(){
        return "order-complete";
    }

}
