package com.nfjs.fooddelivery.menu.validation;

import com.nfjs.fooddelivery.common.excetpion.MenuException;
import com.nfjs.fooddelivery.menu.dto.MenuAddRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuUpdateRequestDto;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Pattern;

import static com.nfjs.fooddelivery.common.excetpion.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MenuValidation {
    private final MenuRepository menuRepository;
    private final ShopRepository shopRepository;

    public void addMenuValidation(MenuAddRequestDto requestDto, UUID shopId, User user) {
        if (user.getRole().equals(UserRoleEnum.CUSTOMER)) {
            throw new MenuException(MENU_PERMISSION_DENIED);
        }

        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", requestDto.menuName())) {
            throw new MenuException(INVALID_MENU_NAME);
        }

        Shop shop = shopRepository.findById(shopId).orElseThrow();

        if (menuRepository.existsByShopAndMenuNameEquals(shop, requestDto.menuName())) {
            throw new MenuException(DUPLICATE_MENU_NAME);
        }

        if (requestDto.menuPrice() < 0) {
            throw new MenuException(INVALID_MENU_PRICE);
        }

    }

    public void updateValidation(MenuUpdateRequestDto requestDto, User user) {
        if (user.getRole().equals(UserRoleEnum.CUSTOMER)) {
            throw new MenuException(MENU_PERMISSION_DENIED);
        }

        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", requestDto.menuName())) {
            throw new MenuException(INVALID_MENU_NAME);
        }

        Shop shop = shopRepository.findById(requestDto.shopId()).orElseThrow();

        if (menuRepository.existsByShopAndMenuNameEquals(shop, requestDto.menuName())) {
            throw new MenuException(DUPLICATE_MENU_NAME);
        }

        if (requestDto.menuPrice() < 0) {
            throw new MenuException(INVALID_MENU_PRICE);
        }

        if (!user.getUserId().equals(shop.getUser().getUserId())) {
            throw new MenuException(SHOP_OWNER_MISMATCH);
        }
    }
}
