package com.mum.onetech.controller;

import com.mum.onetech.domain.OrderItem;
import com.mum.onetech.domain.OrderItemStatus;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.service.OrderItemService;
import com.mum.onetech.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private SellerService sellerService;

    @ModelAttribute("orderItems")
    public List<OrderItem> allOrderItems(){
//        if(authentication != null){
//            System.out.println("****************"+authentication.getName());
//            Seller seller=sellerService.findOneByEmail(authentication.getName());
//            System.out.println("****************"+seller );
//            model.addAttribute("seller",seller);
//            return orderItemService.findListOfOrderListBySelle(seller);
//        }
        return orderItemService.findAll();
    }

    @GetMapping("/allOrderItem")
    public String getAllOrderItem( Model model){
        List<String> itemStatuses = new ArrayList<>();
        itemStatuses.add("PENDING");
        itemStatuses.add("Delivered");
        model.addAttribute("itemStatuses",itemStatuses);
        return "orderItemList";
    }

    @PostMapping("/OrderItemStatusUpdate")
    public @ResponseBody OrderItem updateShipped(@RequestBody OrderItem orderItem){
        System.out.println("product"+orderItem);
        OrderItem orderItem1=orderItem;
        if(orderItem.getId()!=null){
           orderItem1=orderItemService.findById(orderItem.getId()) ;
            orderItem1.setOrderItemStatus(OrderItemStatus.SHIPPED);
            orderItem1=orderItemService.save(orderItem1);
        }
        else {

        }

        return orderItem1;
    }


    @PostMapping("/OrderItemStatusCancelled")
    public @ResponseBody OrderItem updateCancelled(@RequestBody OrderItem orderItem){
        System.out.println("product"+orderItem);
        OrderItem orderItem1=orderItem;
        if(orderItem.getId()!=null){
            orderItem1=orderItemService.findById(orderItem.getId()) ;
            orderItem1.setOrderItemStatus(OrderItemStatus.CANCELLED);
            orderItem1=orderItemService.save(orderItem1);
        }
        else {

        }

        return orderItem1;
    }
}
