package com.nfjs.fooddelivery.deliveryaddress.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_delivery_addresses")
public class DeliveryAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "delivery_address_id")
    private UUID deliveryAddressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "address_name", nullable = false)
    private String addressName;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "postal_address", nullable = false, length = 5)
    private Integer postalAddress;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;
}
