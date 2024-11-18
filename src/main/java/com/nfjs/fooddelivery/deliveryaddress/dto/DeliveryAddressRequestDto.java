package com.nfjs.fooddelivery.deliveryaddress.dto;

import com.nfjs.fooddelivery.deliveryaddress.entity.DeliveryAddress;
import com.nfjs.fooddelivery.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeliveryAddressRequestDto {
    private String addressName;
    private String streetAddress;
    private String detailAddress;
    private Integer postalAddress;
    private Boolean isDefault;

    public DeliveryAddress toEntity(User user) {
        return new DeliveryAddress(
                user,
                this.addressName,
                this.streetAddress,
                this.detailAddress,
                this.postalAddress,
                this.isDefault
        );
    }
}
