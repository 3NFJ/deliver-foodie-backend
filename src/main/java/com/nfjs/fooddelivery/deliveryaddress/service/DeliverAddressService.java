package com.nfjs.fooddelivery.deliveryaddress.service;

import com.nfjs.fooddelivery.common.excetpion.ErrorCode;
import com.nfjs.fooddelivery.deliveryaddress.dto.DeliveryAddressRequestDto;
import com.nfjs.fooddelivery.deliveryaddress.dto.DeliveryAddressResponseDto;
import com.nfjs.fooddelivery.deliveryaddress.entity.DeliveryAddress;
import com.nfjs.fooddelivery.deliveryaddress.repository.DeliveryAddressRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliverAddressService {
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserRepository userRepository;

    public DeliveryAddressResponseDto addAddress(DeliveryAddressRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(ErrorCode.USER_NOT_FOUND.getMessage()));

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
