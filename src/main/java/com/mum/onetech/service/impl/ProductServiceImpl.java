package com.mum.onetech.service.impl;

import com.mum.onetech.domain.Product;
import com.mum.onetech.repository.ProductRepository;
import com.mum.onetech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
