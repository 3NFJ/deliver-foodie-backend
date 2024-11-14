package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.common.excetpion.ErrorCode;
import com.nfjs.fooddelivery.common.excetpion.ShopException;
import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    @Override
    public ShopResponseDto createShop(ShopRequestDto requestDto) {
        // 회원 검증

        String requestShopName = requestDto.name();
        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", requestShopName)) {
            throw new ShopException(ErrorCode.INVALID_SHOP_NAME);
        }

        List<Shop> shopList = shopRepository.findAll();
        for (Shop shop : shopList) {
            if (shop.getShopName().equals(requestShopName)) {
                throw new ShopException(ErrorCode.DUPLICATE_SHOP_NAME);
            }
        }

        Shop entity = shopRepository.save(ShopRequestDto.toEntity(requestDto));

        return ShopResponseDto.from(entity);
    }
}
