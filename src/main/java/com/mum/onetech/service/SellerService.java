package com.mum.onetech.service;

import com.mum.onetech.domain.Seller;

public interface SellerService {
    Seller save(Seller seller);
    Seller register(Seller seller);
    Seller findOneByEmail(String email);
    Seller findById(Long sid);

}
