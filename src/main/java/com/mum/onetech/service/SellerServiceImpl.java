package com.mum.onetech.service;

import com.mum.onetech.domain.Seller;
import com.mum.onetech.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller register(Seller seller) {
        seller.getCredentials().setPassword(passwordEncoder.encode(seller.getCredentials().getPassword()));
        return sellerRepository.save(seller);
    }

    @Override
    public Seller findOneByEmail(String email) {
        return sellerRepository.findOneByEmail(email);
    }


    @Override
    public Seller findById(Long sid) {
        return sellerRepository.findById(sid).orElse(null);
    }
}
