package com.nfjs.fooddelivery.order.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.order.dto.OrderCreateRequestDto;
import com.nfjs.fooddelivery.order.enums.OrderStatus;
import com.nfjs.fooddelivery.order.enums.PaymentMethod;
import com.nfjs.fooddelivery.order.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "p_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "user_id", nullable = false)
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id", nullable = false)
    // UUID Type -> User Type 변경 예정
    private Long userId;

    @Column(name = "shop_id", nullable = false)
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "shop_id", nullable = false)
    // UUID Type -> Shop Type 변경 예정
    private UUID shopId;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "delivery_fee", nullable = false)
    private Integer deliveryFee;

    @Column(name ="discount_amount", nullable = false)
    private Integer discountAmount;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @Column(name = "orderer_name", nullable = false)
    private String ordererName;

    @Column(name = "orderer_phone", nullable = false)
    private String ordererPhone;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "delivery_request")
    private String deliveryRequest;

    @Column(name = "shop_request")
    private String shopRequest;

    public Order(OrderCreateRequestDto orderCreateRequestDto) {
        this.userId = orderCreateRequestDto.getUserId();
        this.shopId = orderCreateRequestDto.getShopId();
        this.orderNumber = LocalDate.now().toString().replace("-","");
        this.orderStatus = OrderStatus.PENDING;
        this.paymentMethod = orderCreateRequestDto.getPaymentMethod();
        this.paymentStatus = PaymentStatus.REQUESTED;
        this.deliveryFee = orderCreateRequestDto.getDeliveryFee();
        this.discountAmount = orderCreateRequestDto.getDiscountAmount();
        this.totalAmount = orderCreateRequestDto.getTotalAmount();
        this.ordererName = orderCreateRequestDto.getOrdererName();
        this.ordererPhone = orderCreateRequestDto.getOrdererPhone();
        this.deliveryAddress = orderCreateRequestDto.getDeliveryAddress();
        this.deliveryRequest = orderCreateRequestDto.getDeliveryRequest();
        this.shopRequest = orderCreateRequestDto.getShopRequest();
    }
}