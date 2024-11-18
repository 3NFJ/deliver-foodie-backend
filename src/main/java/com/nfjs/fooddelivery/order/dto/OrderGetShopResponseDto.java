package com.nfjs.fooddelivery.order.dto;

import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.order.enums.OrderStatus;
import com.nfjs.fooddelivery.order.enums.PaymentMethod;
import com.nfjs.fooddelivery.order.enums.PaymentStatus;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class OrderGetShopResponseDto {

    private UUID orderId;
    private UUID shopId;
    private String orderNumber;;
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
    private LocalDateTime orderCreatedAt;

    public OrderGetShopResponseDto(Order order, Shop shop) {
        this.orderId = order.getOrderId();
        this.shopId = shop.getShopId();
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
        this.orderCreatedAt = order.getCreatedAt();
    }
}
