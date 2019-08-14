package com.mum.onetech.service.impl;

import com.mum.onetech.domain.Review;
import com.mum.onetech.domain.ReviewStatus;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.repository.ReviewRepository;
import com.mum.onetech.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<Review> findByStatus(ReviewStatus status) {
        return reviewRepository.findByStatus(status.name());
    }
}
