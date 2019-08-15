package com.mum.onetech.service;

import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.Seller;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Product findById(Long pid);
    List<Product> findAllByCategoryId(Long catId);
    List<Product> findAllBySellerId(Long sid);
    Long getCountByCategoryId(Long catId);
    Long getCountBySellerId(Long catId);
    Long getCountAll();
    Product getOneProductById(Long id);
    Product delete(Product product);
    void update(Product product);
    List<Product> findListOfProductBySeller(Seller seller);
}
