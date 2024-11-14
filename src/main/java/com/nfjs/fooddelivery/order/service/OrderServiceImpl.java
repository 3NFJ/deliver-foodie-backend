package com.nfjs.fooddelivery.order.service;

import com.nfjs.fooddelivery.order.dto.OrderCreateRequestDto;
import com.nfjs.fooddelivery.order.dto.OrderCreateResponseDto;
import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.order.repository.OrderRepository;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {

        log.info("주문 등록 서비스 호출 : START");
        Shop shop = shopRepository.findById(orderCreateRequestDto.getShopId()).get();
        User user = userRepository.findById(orderCreateRequestDto.getUserId()).get();
        Order saveOrder = orderRepository.save(new Order(orderCreateRequestDto,user,shop));
        log.info("주문 등록 서비스 호출 : END");
        return new OrderCreateResponseDto(saveOrder);
    }
}
