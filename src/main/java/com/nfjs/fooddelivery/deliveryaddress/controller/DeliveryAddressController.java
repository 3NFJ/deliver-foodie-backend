package com.nfjs.fooddelivery.deliveryaddress.controller;

import com.nfjs.fooddelivery.deliveryaddress.dto.DeliveryAddressRequestDto;
import com.nfjs.fooddelivery.deliveryaddress.dto.DeliveryAddressResponseDto;
import com.nfjs.fooddelivery.deliveryaddress.service.DeliverAddressService;
import com.nfjs.fooddelivery.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeliveryAddressController {
    private final DeliverAddressService deliverAddressService;

    @PostMapping("/addresses")
    public ResponseEntity<DeliveryAddressResponseDto> addAddress(@RequestBody DeliveryAddressRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        DeliveryAddressResponseDto responseDto = deliverAddressService.addAddress(requestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
