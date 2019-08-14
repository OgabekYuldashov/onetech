package com.mum.onetech.service;

import com.mum.onetech.domain.Seller;

import java.util.List;

public interface SellerService {
    Seller registerSeller(Seller seller);
    List<Seller> findAllSellers();
    List<Seller> getVerifiedSellers();
    List<Seller> getUnverifiedSellers();

    Seller save(Seller seller);

    Seller findSellerById(Long id);
}
