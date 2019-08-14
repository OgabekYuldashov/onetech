package com.mum.onetech.repository;

import com.mum.onetech.domain.Seller;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<Seller,Long> {
    @Query("SELECT s FROM Seller s WHERE s.credentials.verified = 1")
    public List<Seller> findVerifiedSellers();


    @Query("SELECT s FROM Seller s WHERE s.credentials.verified = 0 ")
    public List<Seller> findUnVerifiedSellers();
}

