package com.nfjs.fooddelivery.shop.dto;

import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

public record ShopRequestDto(
        UUID userId,           // 회원 ID
        String name,           // 가게명
        UUID categoryId,       // 카테고리 ID
        String address,        // 가게 주소
        String phoneNumber,    // 전화번호
        LocalTime openingTime, // 여는 시간
        LocalTime closingTime, // 닫는 시간
        int minOrderAmount,    // 최소 주문 금액
        ShopStatus shopStatus  // 영업 여부
) {

}