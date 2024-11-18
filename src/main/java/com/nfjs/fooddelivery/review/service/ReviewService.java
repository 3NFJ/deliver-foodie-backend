package com.nfjs.fooddelivery.review.service;

import com.nfjs.fooddelivery.review.dto.ReviewCreateRequestDto;
import com.nfjs.fooddelivery.review.dto.ReviewCreateResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface ReviewService {

    ReviewCreateResponseDto createReview(ReviewCreateRequestDto reviewCreateRequestDto, UserDetails userDetails);
}
