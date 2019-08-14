package com.mum.onetech.service.impl;

import com.mum.onetech.domain.Buyer;
import com.mum.onetech.repository.BuyerRepository;
import com.mum.onetech.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    BuyerRepository buyerRepository;

    @Override
    public Buyer addNew(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @Override
    public Buyer findById(Long bid) {
        return buyerRepository.findById(bid).orElse(null);
    }

    @Override
    public List<Buyer> findAll() {
        return (List<Buyer>) buyerRepository.findAll();
    }
}
