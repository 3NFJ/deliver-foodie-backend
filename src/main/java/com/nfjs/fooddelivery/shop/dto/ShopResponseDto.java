package com.nfjs.fooddelivery.shop.dto;

import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;

import java.time.LocalTime;
import java.util.UUID;

public record ShopResponseDto(
        UUID shopId,
        UUID userId,
        String name,
        UUID categoryId,
        String address,
        String phoneNumber,
        LocalTime openingTime,
        LocalTime closingTime,
        int minOrderAmount,
        ShopStatus shopStatus
) {
    public static ShopResponseDto from(Shop shop) {
        return new ShopResponseDto(
                shop.getShopId(),
                shop.getUserId(),
                shop.getName(),
                shop.getCategoryId(),
                shop.getAddress(),
                shop.getPhoneNumber(),
                shop.getOpeningTime(),
                shop.getClosingTime(),
                shop.getMinOrderAmount(),
                shop.getShopStatus()
        );
    }
}
