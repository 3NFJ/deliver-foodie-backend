package com.nfjs.fooddelivery.review.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewModifyRequestDto {

    private Long userId;
    private UUID shopId;
    private UUID orderId;
    private String content;
    private Integer rating;
    private String reviewImage;
}
