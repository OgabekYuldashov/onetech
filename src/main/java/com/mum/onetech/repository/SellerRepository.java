package com.mum.onetech.repository;

import com.mum.onetech.domain.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends CrudRepository<Seller,Long> {

    @Query(value = "SELECT s FROM Seller s WHERE s.credentials.email = :email")
    Seller findOneByEmail(@Param("email") String email);
}
