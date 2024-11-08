package com.nfjs.fooddelivery.shop.entitiy;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "shops")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {
    @Id
    @GeneratedValue
    @Column(name = "shop_id")
    private UUID shopId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String name;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "opening_time", nullable = false)
    private LocalTime openingTime;

    @Column(name = "closing_time", nullable = false)
    private LocalTime closingTime;

    @Column(name = "min_order_amount", nullable = false)
    private int minOrderAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShopStatus shopStatus;


    public static Shop toEntity(ShopRequestDto requestDto) {
        return Shop.builder()
                .userId(requestDto.getUserId())
                .name(requestDto.getName())
                .categoryId(requestDto.getCategoryId())
                .address(requestDto.getAddress())
                .phoneNumber(requestDto.getPhoneNumber())
                .openingTime(requestDto.getOpeningTime())
                .closingTime(requestDto.getClosingTime())
                .minOrderAmount(requestDto.getMinOrderAmount())
                .shopStatus(requestDto.getShopStatus())
                .build();
    }
}
