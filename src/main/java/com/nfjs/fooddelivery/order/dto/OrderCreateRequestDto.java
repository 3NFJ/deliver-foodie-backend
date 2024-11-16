package com.nfjs.fooddelivery.order.dto;

import com.nfjs.fooddelivery.order.enums.PaymentMethod;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class OrderCreateRequestDto {
    private Long userId;
    private UUID shopId;
    private List<MenuDto> menuList;
    private PaymentMethod paymentMethod;
    private Integer deliveryFee;
    private Integer discountAmount;
    private Integer totalAmount;
    private String ordererName;
    private String ordererPhone;
    private String deliveryAddress;
    private String deliveryRequest;
    private String shopRequest;

    @Getter
    public static class MenuDto {
        private UUID menuId;
        private String menuName;
        private Integer quantity;
        private Integer menuPrice;
    }
}