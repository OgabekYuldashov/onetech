package com.mum.onetech.service;

import com.mum.onetech.domain.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();
    Brand finById(Long cid);
}
