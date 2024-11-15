package com.nfjs.fooddelivery.order.service;

import com.nfjs.fooddelivery.order.dto.OrderCreateRequestDto;
import com.nfjs.fooddelivery.order.dto.OrderCreateResponseDto;
import com.nfjs.fooddelivery.order.dto.OrderModifyStatusRequestDto;
import com.nfjs.fooddelivery.order.dto.OrderModifyStatusResponseDto;

import java.util.UUID;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    OrderModifyStatusResponseDto modifyOrderStatus(OrderModifyStatusRequestDto orderModifyStatusRequestDto, UUID orderId);
}
