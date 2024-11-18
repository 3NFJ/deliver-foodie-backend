package com.nfjs.fooddelivery.review.dto;

import com.nfjs.fooddelivery.review.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ReviewGetShopDto {

    private Long userId;
    private UUID orderId;
    private String nickname;
    private String content;
    private Integer rating;
    private LocalDateTime reviewCreatedAt;

    public ReviewGetShopDto(Review review) {
        this.userId = review.getUser().getUserId();
        this.nickname = review.getUser().getNickname();
        this.orderId = review.getOrder().getOrderId();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.reviewCreatedAt = review.getCreatedAt();
    }
}
