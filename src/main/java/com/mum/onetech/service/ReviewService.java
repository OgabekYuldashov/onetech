package com.mum.onetech.service;

        import com.mum.onetech.domain.*;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Service;

        import java.util.List;

 public interface ReviewService {
   //  List<Review> findByStatus(ReviewStatus status);
     List<Review> findReviewByStatusApproved(ReviewStatus status);
    List<Review> findReviewByStatusPending(ReviewStatus status);
    List<Review> findAll();
}
