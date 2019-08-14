package com.mum.onetech.service;

import com.mum.onetech.domain.BuyerOrder;

import java.util.List;

public interface OrderService {
    BuyerOrder save(BuyerOrder buyerOrder);
    BuyerOrder findById(Long oid);
    List<BuyerOrder> findAll();
}
