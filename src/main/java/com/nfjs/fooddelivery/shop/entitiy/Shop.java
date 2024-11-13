package com.nfjs.fooddelivery.shop.entitiy;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
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
@Table(name = "shops", uniqueConstraints = {
        @UniqueConstraint(columnNames = "shop_name")
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "shop_id")
    private UUID shopId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shop_name",nullable = false)
    private String shopName;

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


    public void update(ShopRequestDto requestDto) {
        this.shopName = requestDto.name();
        this.categoryId = requestDto.categoryId();
        this.address = requestDto.address();
        this.phoneNumber = requestDto.phoneNumber();
        this.openingTime = requestDto.openingTime();
        this.closingTime = requestDto.closingTime();
        this.minOrderAmount = requestDto.minOrderAmount();
        this.shopStatus = requestDto.shopStatus();
    }
}
