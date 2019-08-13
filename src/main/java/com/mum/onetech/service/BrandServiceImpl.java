package com.mum.onetech.service;

import com.mum.onetech.domain.Brand;
import com.mum.onetech.domain.Category;
import com.mum.onetech.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return (List<Brand>)brandRepository.findAll();
    }

    @Override
    public Brand finById(Long cid) {
        return null;
    }
}
