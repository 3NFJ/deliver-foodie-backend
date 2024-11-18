package com.nfjs.fooddelivery.deliveryaddress.service;

import com.nfjs.fooddelivery.deliveryaddress.dto.DeliveryAddressRequestDto;
import com.nfjs.fooddelivery.deliveryaddress.dto.DeliveryAddressResponseDto;
import com.nfjs.fooddelivery.deliveryaddress.entity.DeliveryAddress;
import com.nfjs.fooddelivery.deliveryaddress.repository.DeliveryAddressRepository;
import com.nfjs.fooddelivery.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliverAddressService {
    private final DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressResponseDto addAddress(DeliveryAddressRequestDto requestDto, User user) {

        validateRequest(requestDto);

        DeliveryAddress entity = deliveryAddressRepository.save(requestDto.toEntity(user));

        return DeliveryAddressResponseDto.from(entity);
    }


    private void validateRequest(DeliveryAddressRequestDto requestDto) {
        if (requestDto.getAddressName().isBlank()) {
            throw new IllegalStateException();
        }
        if (requestDto.getStreetAddress().isBlank()) {
            throw new IllegalStateException();
        }
        if (requestDto.getPostalAddress() == null) {
            throw new IllegalStateException();
        }
    }
}
