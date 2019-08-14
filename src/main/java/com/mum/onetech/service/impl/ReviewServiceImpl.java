package com.mum.onetech.service.impl;

import com.mum.onetech.domain.PromoteType;
import com.mum.onetech.domain.Review;
import com.mum.onetech.domain.ReviewStatus;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.repository.ReviewRepository;
import com.mum.onetech.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> findReviewByStatusApproved(ReviewStatus status) {
        return reviewRepository.findReviewByStatusApproved(status.toString());
    }

    @Override
    public List<Review> findReviewByStatusPending(ReviewStatus status) {
        return reviewRepository.findReviewByStatusPending(status.toString());
    }

    @Override
    public List<Review> findAll() {
        return (List<Review>) reviewRepository.findAll();
    }


//    @Override
//    public List<Review> findByStatus(ReviewStatus status) {
//        return reviewRepository.findByStatus(status.name());
//    }
}
