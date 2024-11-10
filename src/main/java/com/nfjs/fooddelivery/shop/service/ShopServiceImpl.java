package com.nfjs.fooddelivery.shop.service;

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
            throw new IllegalStateException("가게 이름은 한글, 영어, 숫자만 포함 가능합니다.");
        }

        List<Shop> shopList = shopRepository.findAll();
        for (Shop shop : shopList) {
            if (shop.getName().equals(requestShopName)) {
                throw new IllegalStateException("이미 존재하는 가게명 입니다.");
            }
        }

        Shop entity = shopRepository.save(Shop.toEntity(requestDto));

        return ShopResponseDto.from(entity);
    }
}
