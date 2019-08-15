package com.mum.onetech.service;

import com.mum.onetech.domain.OrderItem;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public List<OrderItem> findAll() {
        return (List<OrderItem>)orderItemRepository.findAll();
    }

    @Override
    public List<OrderItem> findListOfOrderListBySelle(Seller seller) {
        return orderItemRepository.findListOfOrderListBySeller(seller);
    }

    @Override
    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id).get();
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
