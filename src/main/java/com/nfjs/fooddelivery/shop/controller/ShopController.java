package com.nfjs.fooddelivery.shop.controller;

import com.nfjs.fooddelivery.security.UserDetailsImpl;
import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShopController {

    private final ShopService shopService;


    @PostMapping("/shops")
    public ResponseEntity<ShopResponseDto> createShop(@RequestBody ShopRequestDto shopRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ShopResponseDto responseDto = shopService.createShop(shopRequestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/shops/{shopId}")
    public ResponseEntity<ShopResponseDto> updateShop(@PathVariable UUID shopId, @RequestBody ShopRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ShopResponseDto responseDto = shopService.updateShop(shopId, requestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/shops/{shopId}")
    public ResponseEntity<Void> deleteShop(@PathVariable UUID shopId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        shopService.deleteShop(shopId, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/shops")
    public ResponseEntity<List<ShopResponseDto>> getShopList(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        List<ShopResponseDto> shopList = shopService.getShopList(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(shopList);
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<ShopResponseDto> getShopDetail(@PathVariable UUID shopId) {
        ShopResponseDto responseDto = shopService.getShopDetail(shopId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
