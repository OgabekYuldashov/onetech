package com.mum.onetech.service.impl;

import com.mum.onetech.domain.Seller;
import com.mum.onetech.repository.SellerRepository;
import com.mum.onetech.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImp implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Override
    public Seller registerSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public List<Seller> findAllSellers() {
        return (List<Seller>) sellerRepository.findAll();
    }

    @Override
    public List<Seller> getVerifiedSellers() {
        return sellerRepository.findVerifiedSellers();
    }

    @Override
    public List<Seller> getUnverifiedSellers() {
        return sellerRepository.findUnVerifiedSellers();
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller findSellerById(Long id) {
        return sellerRepository.findSellerById(id);
    }


}
