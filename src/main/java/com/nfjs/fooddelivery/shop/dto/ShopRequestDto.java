package com.nfjs.fooddelivery.shop.dto;

import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
public class ShopRequestDto {
    private UUID userId; // 회원 ID
    private String name; // 가게명
    private UUID categoryId; // 카테고리 ID
    private String address; // 가게주소
    private String phoneNumber; // 전화번호
    private LocalTime openingTime; // 여는 시간
    private LocalTime closingTime; // 닫는 시간
    private int minOrderAmount; // 최소주문금액
    private ShopStatus shopStatus; // 영업여부
}