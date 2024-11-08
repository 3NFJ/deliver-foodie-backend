package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    @Override
    public ShopResponseDto createShop(ShopRequestDto requestDto) {
        // 회원 검증

        Shop saveShop = shopRepository.save(Shop.toDomain(requestDto));


        return ShopResponseDto.from(saveShop);
    }
}
