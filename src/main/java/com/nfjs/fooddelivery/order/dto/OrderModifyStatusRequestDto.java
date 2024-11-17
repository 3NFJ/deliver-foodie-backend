package com.nfjs.fooddelivery.order.dto;

import com.nfjs.fooddelivery.order.enums.OrderStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderModifyStatusRequestDto {

    private Long userId;
    private UUID shopId;
    private OrderStatus orderStatus;
}