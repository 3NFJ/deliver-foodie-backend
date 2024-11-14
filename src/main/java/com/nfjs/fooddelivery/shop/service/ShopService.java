package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;

import java.util.UUID;

public interface ShopService {

    ShopResponseDto createShop(ShopRequestDto requestDto);

    ShopResponseDto updateShop(UUID shopId, ShopRequestDto requestDto);
}
