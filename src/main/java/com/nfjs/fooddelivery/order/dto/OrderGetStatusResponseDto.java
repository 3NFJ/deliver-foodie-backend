package com.nfjs.fooddelivery.order.dto;

import com.nfjs.fooddelivery.order.enums.OrderStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderGetStatusResponseDto {

    private Long userId;
    private UUID shopId;
    private UUID orderId;
    private OrderStatus orderStatus;

    public OrderGetStatusResponseDto(Long userId, UUID shopId, UUID orderId, OrderStatus orderStatus) {
        this.userId = userId;
        this.shopId = shopId;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}