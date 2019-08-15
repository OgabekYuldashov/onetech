package com.mum.onetech.service;

import com.mum.onetech.domain.Buyer;

import java.util.List;

public interface BuyerService {
    Buyer save(Buyer buyer);
    Buyer register(Buyer buyer);
    Buyer findById(Long bid);
    Buyer findByEmail(String email);
    List<Buyer> findAll();
}
