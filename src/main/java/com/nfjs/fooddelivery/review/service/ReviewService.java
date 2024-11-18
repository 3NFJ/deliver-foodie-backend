package com.nfjs.fooddelivery.review.service;

import com.nfjs.fooddelivery.review.dto.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface ReviewService {

    ReviewCreateResponseDto createReview(ReviewCreateRequestDto reviewCreateRequestDto, UserDetails userDetails);

    ReviewModifyResponseDto modifyReview(UUID reviewId, ReviewModifyRequestDto reviewModifyRequestDto, UserDetails userDetails);

    ReviewGetResponseDto getReview(UUID reviewId, UserDetails userDetails);
}
