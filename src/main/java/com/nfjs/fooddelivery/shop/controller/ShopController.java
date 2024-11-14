package com.nfjs.fooddelivery.shop.controller;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;


    @PostMapping("/shops")
    public ResponseEntity<ShopResponseDto> createShop(@RequestBody ShopRequestDto shopRequestDto) {
        ShopResponseDto responseDto = shopService.createShop(shopRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
