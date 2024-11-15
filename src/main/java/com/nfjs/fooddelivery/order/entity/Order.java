package com.nfjs.fooddelivery.order.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.order.dto.OrderCreateRequestDto;
import com.nfjs.fooddelivery.order.enums.OrderStatus;
import com.nfjs.fooddelivery.order.enums.PaymentMethod;
import com.nfjs.fooddelivery.order.enums.PaymentStatus;
import com.nfjs.fooddelivery.ordermenu.entity.OrderMenu;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @OneToMany(mappedBy = "order")
    private List<OrderMenu> orderMenus;

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

    public void modifyStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(OrderCreateRequestDto orderCreateRequestDto, User user, Shop shop) {
        this.user = user;
        this.shop = shop;
        this.orderNumber = LocalDate.now().toString().replace("-","")+Math.random();
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