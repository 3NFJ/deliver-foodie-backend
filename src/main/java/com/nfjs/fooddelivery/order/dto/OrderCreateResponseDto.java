package com.nfjs.fooddelivery.order.dto;

import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.order.enums.OrderStatus;
import com.nfjs.fooddelivery.order.enums.PaymentMethod;
import com.nfjs.fooddelivery.order.enums.PaymentStatus;
import lombok.Getter;

@Getter
public class OrderCreateResponseDto {
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

    public OrderCreateResponseDto(Order saveOrder) {
        this.orderNumber = saveOrder.getOrderNumber();
        this.orderStatus = saveOrder.getOrderStatus();
        this.paymentMethod = saveOrder.getPaymentMethod();
        this.paymentStatus = saveOrder.getPaymentStatus();
        this.deliveryFee = saveOrder.getDeliveryFee();
        this.discountAmount = saveOrder.getDiscountAmount();
        this.totalAmount = saveOrder.getTotalAmount();
        this.ordererName = saveOrder.getOrdererName();
        this.ordererPhone = saveOrder.getOrdererPhone();
        this.deliveryAddress = saveOrder.getDeliveryAddress();
        this.deliveryRequest = saveOrder.getDeliveryRequest();
        this.shopRequest = saveOrder.getShopRequest();
    }
}
