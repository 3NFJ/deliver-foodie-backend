package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ShopService {

    ShopResponseDto createShop(ShopRequestDto requestDto);

    ShopResponseDto updateShop(UUID shopId, ShopRequestDto requestDto);

    void deleteShop(UUID shopId, Long userId);

    List<ShopResponseDto> getShopList(Pageable pageable);
}
