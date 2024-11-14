package com.nfjs.fooddelivery.order.service;

import com.nfjs.fooddelivery.order.dto.OrderCreateRequestDto;
import com.nfjs.fooddelivery.order.dto.OrderCreateResponseDto;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);
}
