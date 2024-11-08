package com.nfjs.fooddelivery.shop.dto;

import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Builder
public class ShopResponseDto {
    private UUID shopId;
    private UUID userId;
    private String name;
    private UUID categoryId;
    private String address;
    private String phoneNumber;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private int minOrderAmount;
    private ShopStatus shopStatus;

    public static ShopResponseDto from(Shop saveShop) {
        return ShopResponseDto.builder()
                .shopId(saveShop.getShopId())
                .userId(saveShop.getUserId())
                .name(saveShop.getName())
                .categoryId(saveShop.getCategoryId())
                .address(saveShop.getAddress())
                .phoneNumber(saveShop.getPhoneNumber())
                .openingTime(saveShop.getOpeningTime())
                .closingTime(saveShop.getClosingTime())
                .minOrderAmount(saveShop.getMinOrderAmount())
                .shopStatus(saveShop.getShopStatus())
                .build();
    }
}