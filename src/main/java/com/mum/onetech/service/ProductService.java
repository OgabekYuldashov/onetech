package com.mum.onetech.service;

import com.mum.onetech.domain.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    List<Product> findAllByCategoryId(Long catId);
    Long getCountByCategoryId(Long catId);
    Long getCountAll();
    Product getOneProductById(Long id);
    Product delete(Product product);
    void update(Product product);
}
