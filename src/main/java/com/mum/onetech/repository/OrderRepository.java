package com.mum.onetech.repository;

import com.mum.onetech.domain.BuyerOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<BuyerOrder, Long> {

    /*BuyerOrder save(BuyerOrder buyerOrder);
    BuyerOrder findById(Long oid);
    List<BuyerOrder> findAll();*/
}
