package com.nfjs.fooddelivery.review.dto;

import com.nfjs.fooddelivery.review.entity.Review;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewGetResponseDto {

    private UUID shopId;
    private UUID orderId;
    private UUID reviewId;
    private String shopName;
    private String content;
    private Integer rating;
    private String reviewImage;

    public ReviewGetResponseDto(Review review) {
        this.shopId = review.getShop().getShopId();
        this.orderId = review.getOrder().getOrderId();
        this.reviewId = review.getReviewId();
        this.shopName = review.getShop().getShopName();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.reviewImage = review.getReviewImage();
    }
}
