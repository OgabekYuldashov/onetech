package com.mum.onetech.service;

import com.mum.onetech.domain.Seller;

public interface SellerService {
    Seller save(Seller seller);
    Seller findById(Long sid);

}
