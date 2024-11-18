package com.nfjs.fooddelivery.shop.dto;

import com.nfjs.fooddelivery.category.entity.Category;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import com.nfjs.fooddelivery.user.entity.User;

import java.time.LocalTime;
import java.util.UUID;

public record ShopRequestDto(String name,
                             UUID categoryId,
                             String address,
                             String phoneNumber,
                             LocalTime openingTime,
                             LocalTime closingTime,
                             int minOrderAmount,
                             ShopStatus shopStatus) {

    public static Shop toEntity(ShopRequestDto requestDto, User user, Category category) {
        return Shop.builder()
                .user(user)
                .shopName(requestDto.name)
                .category(category)
                .address(requestDto.address)
                .phoneNumber(requestDto.phoneNumber)
                .openingTime(requestDto.openingTime)
                .closingTime(requestDto.closingTime)
                .minOrderAmount(requestDto.minOrderAmount)
                .shopStatus(requestDto.shopStatus)
                .build();
    }
}