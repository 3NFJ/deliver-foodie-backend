package com.nfjs.fooddelivery.review.controller;

import com.nfjs.fooddelivery.review.dto.ReviewCreateRequestDto;
import com.nfjs.fooddelivery.review.dto.ReviewCreateResponseDto;
import com.nfjs.fooddelivery.review.service.ReviewService;
import com.nfjs.fooddelivery.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<ReviewCreateResponseDto> createReview(
            @RequestBody ReviewCreateRequestDto reviewCreateRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        log.info("리뷰 등록 URL 맵핑 : OK");
        ReviewCreateResponseDto responseDto = reviewService.createReview(reviewCreateRequestDto,userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
