package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.category.entity.Category;
import com.nfjs.fooddelivery.category.repository.CategoryRepository;
import com.nfjs.fooddelivery.common.excetpion.ErrorCode;
import com.nfjs.fooddelivery.common.excetpion.ShopException;
import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ShopResponseDto createShop(ShopRequestDto requestDto, User user) {
        // 회원 검증
        Category category = categoryRepository.findById(requestDto.categoryId()).orElseThrow();
        if (user.getRole().equals(UserRoleEnum.CUSTOMER)) {
            throw new ShopException(ErrorCode.MENU_PERMISSION_DENIED);
        }

        String shopName = requestDto.name();
        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", shopName)) {
            throw new ShopException(ErrorCode.INVALID_SHOP_NAME);
        }

        List<Shop> shopList = shopRepository.findAll();
        for (Shop shop : shopList) {
            if (shop.getShopName().equals(shopName)) {
                throw new ShopException(ErrorCode.DUPLICATE_SHOP_NAME);
            }
        }

        Shop entity = shopRepository.save(ShopRequestDto.toEntity(requestDto, user, category));

        return ShopResponseDto.from(entity);
    }

    @Override
    @Transactional
    public ShopResponseDto updateShop(UUID shopId, ShopRequestDto requestDto, User user) {
        Shop entity = shopRepository.findById(shopId).orElseThrow(() -> new ShopException(ErrorCode.SHOP_NOT_FOUND));
        Category category = categoryRepository.findById(requestDto.categoryId()).orElseThrow();
        if (user.getRole().equals(UserRoleEnum.CUSTOMER)) {
            throw new ShopException(ErrorCode.MENU_PERMISSION_DENIED);
        }

        String shopName = requestDto.name();
        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", shopName)) {
            throw new ShopException(ErrorCode.INVALID_SHOP_NAME);
        }

        entity.update(requestDto, category);

        return ShopResponseDto.from(entity);
    }

    @Override
    @Transactional
    public void deleteShop(UUID shopId, User user) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new ShopException(ErrorCode.SHOP_NOT_FOUND));

        if (!shop.getUser().getUserId().equals(user.getUserId())) {
            throw new ShopException(ErrorCode.MENU_PERMISSION_DENIED);
        }

        shop.delete(user.getUsername());
    }

    @Override
    public List<ShopResponseDto> getShopList(Pageable pageable) {
        Page<Shop> shopList = shopRepository.findAllNonDeletedShops(pageable);

        return shopList.stream()
                .map(ShopResponseDto::from)
                .toList();
    }

    @Override
    public ShopResponseDto getShopDetail(UUID shopId) {
        Shop shop = shopRepository.findByShopIdAndDeletedAtIsNull(shopId).orElseThrow(() -> new ShopException(ErrorCode.SHOP_NOT_FOUND));

        return ShopResponseDto.from(shop);
    }
}
