package com.nfjs.fooddelivery.shop.entitiy;

import com.nfjs.fooddelivery.category.entity.Category;
import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import com.nfjs.fooddelivery.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "p_shops", uniqueConstraints = {
        @UniqueConstraint(columnNames = "shop_name")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "shop_id")
    private UUID shopId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "shop_name",nullable = false)
    private String shopName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "address", nullable = false)
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
    @Column(name = "shop_status", nullable = false)
    private ShopStatus shopStatus;


    public void update(ShopRequestDto requestDto, Category category) {
        this.shopName = requestDto.name();
        this.category = category;
        this.address = requestDto.address();
        this.phoneNumber = requestDto.phoneNumber();
        this.openingTime = requestDto.openingTime();
        this.closingTime = requestDto.closingTime();
        this.minOrderAmount = requestDto.minOrderAmount();
        this.shopStatus = requestDto.shopStatus();
    }

    public void delete(String deletedBy) {
        super.delete(deletedBy);
        this.shopStatus = ShopStatus.CLOSED;
    }
}
