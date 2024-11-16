package com.nfjs.fooddelivery.order.dto;

import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.order.enums.OrderStatus;
import com.nfjs.fooddelivery.order.enums.PaymentMethod;
import com.nfjs.fooddelivery.order.enums.PaymentStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class OrderGetDetailResponseDto {

    private UUID orderId;
    private UUID shopId;
    private String shopName;
    private String orderNumber;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Integer deliveryFee;
    private Integer discountAmount;
    private Integer totalAmount;
    private String ordererName;
    private String ordererPhone;
    private String deliveryAddress;
    private String deliveryRequest;
    private String shopRequest;
    private List<OrderMenuDto> menuList;
    private LocalDateTime orderCreatedAt;

    public OrderGetDetailResponseDto(Order order, List<OrderMenuDto> orderMenuListDto) {
        this.orderId = order.getOrderId();
        this.shopId = order.getShop().getShopId();
        this.shopName = order.getShop().getShopName();
        this.orderNumber = order.getOrderNumber();
        this.orderStatus = order.getOrderStatus();
        this.paymentMethod = order.getPaymentMethod();
        this.paymentStatus = order.getPaymentStatus();
        this.deliveryFee = order.getDeliveryFee();
        this.discountAmount = order.getDiscountAmount();
        this.totalAmount = order.getTotalAmount();
        this.ordererName = order.getOrdererName();
        this.ordererPhone = order.getOrdererPhone();
        this.deliveryAddress = order.getDeliveryAddress();
        this.deliveryRequest = order.getDeliveryRequest();
        this.shopRequest = order.getShopRequest();
        this.menuList = orderMenuListDto;
        this.orderCreatedAt = order.getCreatedAt();
    }
}
