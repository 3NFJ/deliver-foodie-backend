package com.nfjs.fooddelivery.review.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.deliveryaddress.entity.DeliveryAddress;
import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.review.dto.ReviewCreateRequestDto;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_reviews")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "review_id")
    private UUID reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "postal_code", nullable = false, length = 5)
    private Integer postalCode;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "review_image")
    private String reviewImage;

    public Review(ReviewCreateRequestDto reviewCreateRequestDto, Shop shop, Order order, User user, DeliveryAddress deliveryAddress) {
        this.shop = shop;
        this.user = user;
        this.order = order;
        this.streetAddress = deliveryAddress.getStreetAddress();
        this.detailAddress = deliveryAddress.getDetailAddress();
        this.postalCode = deliveryAddress.getPostalAddress();
        this.content = reviewCreateRequestDto.getContent();
        this.rating = reviewCreateRequestDto.getRating();
        this.reviewImage = reviewCreateRequestDto.getReviewImage();
    }
}
