package com.mum.onetech.repository;

import com.mum.onetech.domain.Review;
import com.mum.onetech.domain.ReviewStatus;
import com.mum.onetech.domain.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {


    @Query("SELECT r FROM Review r WHERE r.status = :status")
    public List<Review> findByStatus(@Param("status") String status);

}
