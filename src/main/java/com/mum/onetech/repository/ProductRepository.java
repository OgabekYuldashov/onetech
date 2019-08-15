package com.mum.onetech.repository;

import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long id);

//    @Query(value = "SELECT count(p) FROM Product p WHERE p.category.id = :catId")
    @Query(value = "SELECT COUNT(*) FROM PRODUCT p WHERE p.CATEGORY_ID = :catId", nativeQuery = true)
    Long getCountByCategoryId(@Param("catId") Long catId);

    @Query(value = "SELECT p FROM Product p WHERE p.seller = :seller")
    List<Product> findListOfProductBySeller(@Param("seller") Seller seller);

    @Query(value = "SELECT count(p) FROM Product p")
    Long getCountAll();

}
