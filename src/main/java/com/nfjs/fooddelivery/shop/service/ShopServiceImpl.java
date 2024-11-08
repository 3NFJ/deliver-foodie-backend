package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    @Override
    public ShopResponseDto createShop(ShopRequestDto requestDto) {
        // 회원 검증

        String shopName = requestDto.getName();
        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", shopName)) {
            throw new IllegalStateException("가게 이름은 한글, 영어, 숫자만 포함 가능합니다.");
        }

        Shop entity = shopRepository.save(Shop.toEntity(requestDto));

        return ShopResponseDto.from(entity);
    }
}
