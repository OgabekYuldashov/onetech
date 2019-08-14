package com.mum.onetech.repository;

import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.Review;
import com.mum.onetech.domain.ReviewStatus;
import com.mum.onetech.domain.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {

//    @Query("SELECT r FROM Review  WHERE r.id=:id2 ")
//      public Review findReviewById(@Param("id2") Long id);

    @Query("SELECT r FROM Review r WHERE r.status = :approved")
    public List<Review> findReviewByStatusApproved(@Param("approved") String status);

    @Query("SELECT r FROM Review r WHERE r.status = :pending")
    public List<Review> findReviewByStatusPending(@Param("pending") String status);

}
