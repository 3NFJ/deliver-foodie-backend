package com.nfjs.fooddelivery.shop.service;

import com.nfjs.fooddelivery.category.entity.Category;
import com.nfjs.fooddelivery.category.repository.CategoryRepository;
import com.nfjs.fooddelivery.common.excetpion.ErrorCode;
import com.nfjs.fooddelivery.common.excetpion.ShopException;
import com.nfjs.fooddelivery.shop.dto.ShopRequestDto;
import com.nfjs.fooddelivery.shop.dto.ShopResponseDto;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import com.nfjs.fooddelivery.shop.repository.ShopRepositoryCustom;
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
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepositoryCustom shopRepositoryCustom;

    @Override
    public ShopResponseDto createShop(ShopRequestDto requestDto) {
        // 회원 검증
        User user = userRepository.findById(requestDto.userId()).orElseThrow();
        Category category = categoryRepository.findById(requestDto.categoryId()).orElseThrow();

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

        Shop entity = shopRepository.save(ShopRequestDto.toEntity(requestDto, user, category));

        return ShopResponseDto.from(entity);
    }

    @Override
    @Transactional
    public ShopResponseDto updateShop(UUID shopId, ShopRequestDto requestDto) {
        Shop entity = shopRepository.findById(shopId).orElseThrow(() -> new IllegalStateException(ErrorCode.SHOP_NOT_FOUND.getMessage()));
        Category category = categoryRepository.findById(requestDto.categoryId()).orElseThrow();

        String shopName = requestDto.name();
        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", shopName)) {
            throw new ShopException(ErrorCode.INVALID_SHOP_NAME);
        }

        entity.update(requestDto, category);

        return ShopResponseDto.from(entity);
    }

    @Override
    @Transactional
    public void deleteShop(UUID shopId, Long userId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new IllegalStateException(ErrorCode.SHOP_NOT_FOUND.getMessage()));

        if (!shop.getUser().getUserId().equals(userId)) {
            throw new ShopException(ErrorCode.USER_NOT_MATCH);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(ErrorCode.USER_NOT_FOUND.getMessage()));

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
        Shop shop = shopRepositoryCustom.findValidShopById(shopId).orElseThrow(() -> new NullPointerException("가게 정보를 찾을 수 없습니다."));

        log.info("shop ========{}", shop);
//        Shop entity = shopRepository.findById(shopId).orElseThrow(() -> new NullPointerException("가게 정보를 찾을 수 없습니다."));
//

//        if (entity.getDeletedAt() == null && entity.getShopStatus() == ShopStatus.CLOSED) {
//            return null;
//        }
        return ShopResponseDto.from(shop);
    }
}
