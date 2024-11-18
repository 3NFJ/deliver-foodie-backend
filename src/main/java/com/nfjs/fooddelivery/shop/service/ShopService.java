package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ShopService {

    ShopResponseDto createShop(ShopRequestDto requestDto, User user);

    ShopResponseDto updateShop(UUID shopId, ShopRequestDto requestDto, User userDetails);

    void deleteShop(UUID shopId, User userId);

    Page<ShopResponseDto> getShopList(Pageable pageable);

    ShopResponseDto getShopDetail(UUID shopId);
}
