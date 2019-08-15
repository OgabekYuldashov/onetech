package com.mum.onetech.service;

import com.mum.onetech.domain.Seller;
import com.mum.onetech.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImp implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller findById(Long sid) {
        return sellerRepository.findById(sid).orElse(null);
    }
}
