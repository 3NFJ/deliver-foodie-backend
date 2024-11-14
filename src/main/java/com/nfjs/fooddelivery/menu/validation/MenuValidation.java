package com.nfjs.fooddelivery.menu.validation;

import com.nfjs.fooddelivery.common.excetpion.ErrorCode;
import com.nfjs.fooddelivery.common.excetpion.MenuException;
import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.repository.MenuRepository;
import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MenuValidation {

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

    private static void validateNotNull(MenuRequestDto requestDto) {
        if (requestDto.shopId() == null) {
            throw new IllegalStateException("매장 ID는 필수입니다.");
        }
        if (requestDto.menuName() == null || requestDto.menuName().trim().isEmpty()) {
            throw new IllegalStateException("메뉴명은 필수입니다.");
        }
        if (requestDto.status() == null) {
            throw new IllegalStateException("메뉴 상태는 필수입니다.");
        }
    }
}
