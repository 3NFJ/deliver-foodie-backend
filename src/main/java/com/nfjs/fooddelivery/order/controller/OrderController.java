package com.nfjs.fooddelivery.order.controller;

import com.nfjs.fooddelivery.order.dto.OrderCreateRequestDto;
import com.nfjs.fooddelivery.order.dto.OrderCreateResponseDto;
import com.nfjs.fooddelivery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/shops/{shopId}/orders")
    public ResponseEntity<OrderCreateResponseDto> createShop(
            @PathVariable UUID shopId,
            @RequestBody OrderCreateRequestDto orderCreateRequestDto) {

        log.info("주문 등록 URL 맵핑 : OK");
        OrderCreateResponseDto responseDto = orderService.createOrder(orderCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
