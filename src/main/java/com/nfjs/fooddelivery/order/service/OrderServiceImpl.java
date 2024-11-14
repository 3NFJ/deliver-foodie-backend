package com.nfjs.fooddelivery.order.service;

import com.nfjs.fooddelivery.order.dto.OrderCreateRequestDto;
import com.nfjs.fooddelivery.order.dto.OrderCreateResponseDto;
import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {

        log.info("주문 등록 서비스 호출 : START");
        Order saveOrder = orderRepository.save(new Order(orderCreateRequestDto));
        log.info("주문 등록 서비스 호출 : END");
        return new OrderCreateResponseDto(saveOrder);
    }
}
