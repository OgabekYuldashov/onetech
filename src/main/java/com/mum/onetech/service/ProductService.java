package com.mum.onetech.service;

import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.PromoteType;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Product findById(Long pid);
    List<Product> findAllByCategoryId(Long catId);
    Long getCountByCategoryId(Long catId);
    Long getCountAll();
    Product getOneProductById(Long id);
    Product delete(Product product);
    List<Product> findProductByStatusPromoted(PromoteType promote);
    List<Product> findProductByStatusNotPromoted(PromoteType promote);
    //Product findProductById(Long id);

}
