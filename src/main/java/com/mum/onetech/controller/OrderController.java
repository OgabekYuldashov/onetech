package com.mum.onetech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/listOrder")
    public String listOfOrder(){
        return "order";
    }
}
