package com.mum.onetech.repository;

import com.mum.onetech.domain.OrderItem;
import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem ,Long> {
    @Query(value = "SELECT oi FROM OrderItem oi WHERE oi.product.seller = :seller")
    List<OrderItem> findListOfOrderListBySeller(@Param("seller") Seller seller);
}
