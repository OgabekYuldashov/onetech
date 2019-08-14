package com.mum.onetech.service;

        import com.mum.onetech.domain.Review;
        import com.mum.onetech.domain.ReviewStatus;
        import com.mum.onetech.domain.Seller;
        import org.springframework.data.repository.query.Param;

        import java.util.List;

 public interface ReviewService {
     List<Review> findByStatus(ReviewStatus status);
}
