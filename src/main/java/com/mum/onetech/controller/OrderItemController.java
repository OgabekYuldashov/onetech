package com.mum.onetech.controller;

import com.mum.onetech.domain.OrderItem;
import com.mum.onetech.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @ModelAttribute("orderItems")
    public List<OrderItem> allOrderItems(){
        return orderItemService.findAll();
    }

    @GetMapping("/allOrderItem")
    public String getAllOrderItem(){
        return "orderItemList";
    }
}
