package com.mum.onetech.service;

import com.mum.onetech.domain.Buyer;
import com.mum.onetech.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    BuyerRepository buyerRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Buyer addNew(Buyer buyer) {
        buyer.getCredentials().setPassword(passwordEncoder.encode(buyer.getCredentials().getPassword()));
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
