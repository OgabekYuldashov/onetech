package com.mum.onetech.service;

import com.mum.onetech.domain.BuyerOrder;
import com.mum.onetech.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public BuyerOrder save(BuyerOrder buyerOrder) {
        return orderRepository.save(buyerOrder);
    }

    @Override
    public BuyerOrder findById(Long oid) {
        return orderRepository.findById(oid).orElse(null);
    }

    @Override
    public List<BuyerOrder> findAll() {
        return (List<BuyerOrder>) orderRepository.findAll();
    }
}
