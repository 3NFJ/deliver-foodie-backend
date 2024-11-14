package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;

public interface ShopService {

    ShopResponseDto createShop(ShopRequestDto shopReqDto);
}
