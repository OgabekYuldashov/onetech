package com.mum.onetech.service;

import com.mum.onetech.domain.Buyer;

import java.util.List;

public interface BuyerService {
    Buyer addNew(Buyer buyer);
    Buyer findById(Long bid);
    List<Buyer> findAll();
}
