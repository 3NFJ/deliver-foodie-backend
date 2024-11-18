package com.nfjs.fooddelivery.review.service;

import com.nfjs.fooddelivery.review.dto.ReviewCreateRequestDto;
import com.nfjs.fooddelivery.review.dto.ReviewCreateResponseDto;
import com.nfjs.fooddelivery.review.dto.ReviewModifyRequestDto;
import com.nfjs.fooddelivery.review.dto.ReviewModifyResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface ReviewService {

    ReviewCreateResponseDto createReview(ReviewCreateRequestDto reviewCreateRequestDto, UserDetails userDetails);

    ReviewModifyResponseDto modifyReview(UUID reviewId, ReviewModifyRequestDto reviewModifyRequestDto, UserDetails userDetails);
}
