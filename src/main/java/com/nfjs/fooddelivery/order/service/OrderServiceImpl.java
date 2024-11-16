package com.nfjs.fooddelivery.order.service;

import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import com.nfjs.fooddelivery.order.dto.*;
import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.order.repository.OrderRepository;
import com.nfjs.fooddelivery.ordermenu.entity.OrderMenu;
import com.nfjs.fooddelivery.ordermenu.repository.OrderMenuRepository;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final OrderMenuRepository orderMenuRepository;

    @Override
    @Transactional
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {

        log.info("주문 등록 서비스 호출 : START");
        User user = userRepository.findById(orderCreateRequestDto.getUserId()).orElseThrow();
        Shop shop = shopRepository.findById(orderCreateRequestDto.getShopId()).orElseThrow();
        Order saveOrder = orderRepository.save(new Order(orderCreateRequestDto,user,shop));

        log.info("주문 등록 메뉴 확인 : START");
        for(OrderCreateRequestDto.MenuDto menuDto : orderCreateRequestDto.getMenuList()) {
            Menu menu = menuRepository.findById(menuDto.getMenuId()).orElseThrow();

            log.info("메뉴명: {}, 개수: {}, 가격: {}",menuDto.getMenuName(),menuDto.getQuantity(),menuDto.getMenuPrice());

            orderMenuRepository.save(new OrderMenu(saveOrder,menu,
                    menuDto.getMenuName(),menuDto.getQuantity(),menuDto.getMenuPrice()));
        }
        log.info("주문 등록 메뉴 확인 : END");
        log.info("주문 등록 서비스 호출 : END");

        return new OrderCreateResponseDto(saveOrder);
    }

    @Override
    @Transactional
    public OrderModifyStatusResponseDto modifyOrderStatus(OrderModifyStatusRequestDto orderModifyStatusRequestDto, UUID orderId) {

        log.info("주문 상태 변경 서비스 호출 : START");
        User user = userRepository.findById(orderModifyStatusRequestDto.getUserId()).orElseThrow();
        Shop shop = shopRepository.findById(orderModifyStatusRequestDto.getShopId()).orElseThrow();
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.modifyStatus(orderModifyStatusRequestDto.getOrderStatus());

        log.info("주문 상태 변경 서비스 호출 : END");
        return new OrderModifyStatusResponseDto(user.getUserId(), shop.getShopId(), order.getOrderId(), order.getOrderStatus());
    }

    @Override
    public OrderGetStatusResponseDto getOrderStatus(UUID orderId, UserDetails userDetails) {

        log.info("주문 상태 조회 서비스 호출 : START");
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Order order = orderRepository.findById(orderId).orElseThrow();

        log.info("주문 상태 변경 서비스 호출 : END");
        return new OrderGetStatusResponseDto(user.getUserId(), order.getShop().getShopId(), order.getOrderId(), order.getOrderStatus());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderGetResponseDto> getOrderList(UserDetails userDetails) {

        log.info("주문 목록 조회 서비스 호출 : START");
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Order> orders = orderRepository.findAllByUserOrderByCreatedAtDesc(user);

        log.info("주문 목록 담기 : START");
        List<OrderGetResponseDto> orderGetListResponseDto = new ArrayList<>();
        for(Order order: orders) orderGetListResponseDto.add(new OrderGetResponseDto(order));

        log.info("주문 목록 담기 : END");
        log.info("주문 목록 조회 서비스 호출 : END");
        return orderGetListResponseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderGetDetailResponseDto getOrderDetail(UUID orderId, UserDetails userDetails) {

        log.info("주문 상세 조회 서비스 호출 : START");
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Order order = orderRepository.findById(orderId).orElseThrow();

        log.info("메뉴 담기 : START");
        List<OrderMenuDto> orderMenuListDto = new ArrayList<>();
        for(OrderMenu orderMenu: order.getOrderMenus())
            orderMenuListDto.add(new OrderMenuDto(orderMenu));

        log.info("메뉴 담기 : END");
        log.info("주문 상세 조회 서비스 호출 : END");
        return new OrderGetDetailResponseDto(order,orderMenuListDto);
    }

    @Override
    public List<OrderGetShopResponseDto> getOrderShopList(UUID shopId, UserDetails userDetails) {

        log.info("매장별 주문 목록 조회 서비스 호출 : START");
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        List<Order> orders = orderRepository.findAllByShopOrderByCreatedAtDesc(shop);

        log.info("메뉴 담기 : START");
        List<OrderGetShopResponseDto> orderGetShopListResponseDto = new ArrayList<>();
        for(Order order: orders) orderGetShopListResponseDto.add(new OrderGetShopResponseDto(order,shop));

        log.info("메뉴 담기 : END");
        log.info("주문 상세 조회 서비스 호출 : END");
        return orderGetShopListResponseDto;
    }
}
