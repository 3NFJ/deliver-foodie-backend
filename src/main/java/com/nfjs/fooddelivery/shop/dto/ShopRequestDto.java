package com.nfjs.fooddelivery.shop.dto;

import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;

import java.time.LocalTime;
import java.util.UUID;

public record ShopRequestDto(Long userId,
                             String name,
                             UUID categoryId,
                             String address,
                             String phoneNumber,
                             LocalTime openingTime,
                             LocalTime closingTime,
                             int minOrderAmount,
                             ShopStatus shopStatus) {

    public static Shop toEntity(ShopRequestDto requestDto) {
        return Shop.builder()
                .userId(requestDto.userId)
                .shopName(requestDto.name)
                .categoryId(requestDto.categoryId)
                .address(requestDto.address)
                .phoneNumber(requestDto.phoneNumber)
                .openingTime(requestDto.openingTime)
                .closingTime(requestDto.closingTime)
                .minOrderAmount(requestDto.minOrderAmount)
                .shopStatus(requestDto.shopStatus)
                .build();
    }
}