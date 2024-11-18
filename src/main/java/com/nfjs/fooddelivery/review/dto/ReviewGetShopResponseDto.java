package com.nfjs.fooddelivery.review.dto;

import com.nfjs.fooddelivery.review.entity.Review;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ReviewGetShopResponseDto {

    private UUID shopId;
    private String shopName;
    private Double averageRating;
    private List<ReviewGetShopDto> reviewGetShopDtos;

    public ReviewGetShopResponseDto(Shop shop, Double averageRating, List<ReviewGetShopDto> reviewGetShopDtos) {
        this.shopId = shop.getShopId();
        this.shopName = shop.getShopName();
        this.averageRating = averageRating;
        this.reviewGetShopDtos = reviewGetShopDtos;
    }
}
