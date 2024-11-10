package com.nfjs.fooddelivery.shop.controller;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;


    @PostMapping("/shops")
    public ResponseEntity<ShopResponseDto> createShop(@RequestBody ShopRequestDto shopRequestDto) {
        ShopResponseDto responseDto = shopService.createShop(shopRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/shops/{shopId}")
    public ResponseEntity<ShopResponseDto> updateShop(@PathVariable UUID shopId, @RequestBody ShopRequestDto requestDto) {
        ShopResponseDto responseDto = shopService.updateShop(shopId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/shops/{shopId}")
    public ResponseEntity<Object> deleteShop(@PathVariable UUID shopId, @RequestParam UUID userId) {
        shopService.deleteShop(shopId, userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
