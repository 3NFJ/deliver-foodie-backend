package com.nfjs.fooddelivery.deliveryaddress.dto;

import com.nfjs.fooddelivery.deliveryaddress.entity.DeliveryAddress;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeliveryAddressResponseDto {
    private final UUID deliveryAddressId;
    private final Long userId;
    private final String addressName;
    private final String streetAddress;
    private final String detailAddress;
    private final Integer postalAddress;
    private final Boolean isDefault;

    private DeliveryAddressResponseDto(UUID deliveryAddressId, Long userId, String addressName, String streetAddress, String detailAddress, Integer postalAddress, Boolean isDefault) {
        this.deliveryAddressId = deliveryAddressId;
        this.userId = userId;
        this.addressName = addressName;
        this.streetAddress = streetAddress;
        this.detailAddress = detailAddress;
        this.postalAddress = postalAddress;
        this.isDefault = isDefault;
    }

    public static DeliveryAddressResponseDto from(DeliveryAddress entity) {
        return new DeliveryAddressResponseDto(
                entity.getDeliveryAddressId(),
                entity.getUser().getUserId(),
                entity.getAddressName(),
                entity.getStreetAddress(),
                entity.getDetailAddress(),
                entity.getPostalAddress(),
                entity.getIsDefault()
        );
    }
}
