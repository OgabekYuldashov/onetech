package com.mum.onetech.repository;

import com.mum.onetech.domain.Buyer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BuyerRepository extends CrudRepository<Buyer, Long> {

    @Query(value = "SELECT b FROM Buyer b WHERE b.credentials.email = :email")
    Buyer findOneByEmail(@Param("email") String email);
}
