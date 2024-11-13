package com.nfjs.fooddelivery.menu.validation;

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
            throw new IllegalStateException("메뉴 이름은 한글, 영어, 숫자만 포함 가능합니다.");
        }

        List<Menu> menuList = menuRepository.findAll();
        for (Menu menu : menuList) {
            if (menu.getShopId() == shopId && (menu.getMenuName().equals(requestDto.menuName()))) {
                throw new IllegalStateException("가게에 이미 존재하는 메뉴명 입니다.");
            }
        }

        if (requestDto.menuPrice() < 0) {
            throw new IllegalStateException("유효하지 않은 메뉴 가격입니다.");
        }
    }
}
