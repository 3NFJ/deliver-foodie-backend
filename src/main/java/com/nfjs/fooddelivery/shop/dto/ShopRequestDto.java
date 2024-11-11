package com.nfjs.fooddelivery.shop.dto;

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
}