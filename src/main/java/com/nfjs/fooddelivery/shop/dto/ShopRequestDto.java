package com.nfjs.fooddelivery.shop.dto;

import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

public record ShopRequestDto(UUID userId,
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
                .name(requestDto.name)
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