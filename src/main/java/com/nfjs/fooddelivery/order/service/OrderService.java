package com.nfjs.fooddelivery.order.service;

import com.nfjs.fooddelivery.order.dto.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    OrderModifyStatusResponseDto modifyOrderStatus(OrderModifyStatusRequestDto orderModifyStatusRequestDto, UUID orderId);

    OrderGetStatusResponseDto getOrderStatus(UUID orderId, UserDetails userDetails);

    OrderGetDetailResponseDto getOrderDetail(UUID orderId, UserDetails userDetails);
  
    List<OrderGetResponseDto> getOrderList(UserDetails userDetails, int page, int size);
}