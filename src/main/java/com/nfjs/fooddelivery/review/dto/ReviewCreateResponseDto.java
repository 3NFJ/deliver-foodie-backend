package com.nfjs.fooddelivery.review.dto;

import com.nfjs.fooddelivery.review.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ReviewCreateResponseDto {

    private Long userId;
    private UUID shopId;
    private UUID orderId;
    private String content;
    private Integer rating;
    private LocalDateTime reviewCreatedAt;

    public ReviewCreateResponseDto(Review review) {
        this.userId = review.getUser().getUserId();
        this.shopId = review.getShop().getShopId();
        this.orderId = review.getOrder().getOrderId();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.reviewCreatedAt = review.getCreatedAt();
    }
}
