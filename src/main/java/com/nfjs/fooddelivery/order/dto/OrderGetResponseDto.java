package com.nfjs.fooddelivery.order.dto;

import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.order.enums.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class OrderGetResponseDto {

    private UUID orderId;
    private UUID shopId;
    private String shopName;
    private Integer totalPrice;
    private OrderStatus orderStatus;
    private LocalDateTime ordercCreateAt;

    public OrderGetResponseDto(Order order) {
        this.orderId = order.getOrderId();
        this.shopId = order.getShop().getShopId();
        this.shopName = order.getShop().getShopName();
        this.totalPrice = order.getTotalAmount();
        this.orderStatus = order.getOrderStatus();
        this.ordercCreateAt = order.getCreatedAt();
    }
}
