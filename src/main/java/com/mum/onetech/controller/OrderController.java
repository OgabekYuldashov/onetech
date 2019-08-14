package com.mum.onetech.controller;

import com.mum.onetech.domain.Cart;
import com.mum.onetech.domain.Product;
import com.mum.onetech.jsonmodel.CartItemModel;
import com.mum.onetech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class OrderController {
    @Autowired
    ProductService productService;

    @GetMapping("/listOrder")
    public String listOfOrder(){
        return "order";
    }

    /*@PostMapping("/cart/add")
    @ResponseBody
    public Cart addToCart(@Valid @RequestBody CartItemModel cartItemModel){
        Product p = productService.findById(cartItemModel.getPid());
        if(p != null){

        }
        return categoryService.save(category);
    }*/
}
