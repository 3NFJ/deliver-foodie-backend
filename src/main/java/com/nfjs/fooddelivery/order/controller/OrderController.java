package com.nfjs.fooddelivery.order.controller;

import com.nfjs.fooddelivery.order.dto.*;
import com.nfjs.fooddelivery.order.service.OrderService;
import com.nfjs.fooddelivery.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/shops/{shopId}/orders")
    public ResponseEntity<OrderCreateResponseDto> createOrder(
            @PathVariable UUID shopId,
            @RequestBody OrderCreateRequestDto orderCreateRequestDto) {

        log.info("주문 등록 URL 맵핑 : OK");
        OrderCreateResponseDto responseDto = orderService.createOrder(orderCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/orders/{orderId}/status")
    public ResponseEntity<OrderModifyStatusResponseDto> modifyOrderStatus(
            @PathVariable UUID orderId,
            @RequestBody OrderModifyStatusRequestDto orderModifyStatusRequestDto) {

        log.info("주문 상태 수정 URL 맵핑 : OK");
        OrderModifyStatusResponseDto responseDto = orderService.modifyOrderStatus(orderModifyStatusRequestDto, orderId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/orders/{orderId}/status")
    public ResponseEntity<OrderGetStatusResponseDto> getOrderStatus(
            @PathVariable UUID orderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        log.info("주문 상태 조회 URL 맵핑 : OK");
        OrderGetStatusResponseDto responseDto = orderService.getOrderStatus(orderId,userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    
    @GetMapping("/orders/")
    public ResponseEntity<List<OrderGetResponseDto>> getOrderList(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        log.info("주문 목록 조회 URL 맵핑 : OK");
        List<OrderGetResponseDto> responseDto = orderService.getOrderList(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderGetDetailResponseDto> getOrderDetail(
            @PathVariable UUID orderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        log.info("주문 상세 조회 URL 맵핑 : OK");
        OrderGetDetailResponseDto responseDto = orderService.getOrderDetail(orderId, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
