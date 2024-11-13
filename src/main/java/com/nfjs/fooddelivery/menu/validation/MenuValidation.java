package com.nfjs.fooddelivery.menu.validation;

import com.nfjs.fooddelivery.common.excetpion.ErrorCode;
import com.nfjs.fooddelivery.common.excetpion.MenuException;
import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MenuValidation {
    private final MenuRepository menuRepository;

    public void addMenuValidation(MenuRequestDto requestDto, UUID shopId) {
        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", requestDto.menuName())) {
            throw new MenuException(ErrorCode.INVALID_MENU_NAME);
        }

        if (menuRepository.existsByShopIdAndMenuNameEquals(shopId, requestDto.menuName())) {
            throw new MenuException(ErrorCode.DUPLICATE_MENU_NAME);
        }

        if (requestDto.menuPrice() < 0) {
            throw new MenuException(ErrorCode.INVALID_MENU_PRICE);
        }
    }
}
