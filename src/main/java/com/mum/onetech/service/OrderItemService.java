package com.mum.onetech.service;

import com.mum.onetech.domain.BuyerOrder;
import com.mum.onetech.domain.OrderItem;
import com.mum.onetech.domain.Seller;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAll();
    List<OrderItem> findListOfOrderListBySelle(Seller seller);
    OrderItem findById(Long id);
    OrderItem save(OrderItem orderItem);
}
