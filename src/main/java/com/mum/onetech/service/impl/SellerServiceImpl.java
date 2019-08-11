package com.mum.onetech.service.impl;

import com.mum.onetech.domain.Seller;
import com.mum.onetech.repository.SellerRepository;
import com.mum.onetech.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public List<Seller> findAllSellers() {
        return  (List<Seller>) sellerRepository.findAll();
    }

}
