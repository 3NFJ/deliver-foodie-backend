package com.nfjs.fooddelivery.menu.validation;

import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MenuValidation {

    public void validateMenuDetails(MenuRequestDto requestDto) {
        validateNotNull(requestDto);

        if (!Pattern.matches("^[a-zA-Z가-힣0-9]+$", requestDto.menuName())) {
            throw new IllegalStateException("메뉴 이름은 한글, 영어, 숫자만 포함 가능합니다.");
        }

        if (requestDto.menuPrice() < 0) {
            throw new IllegalStateException("유효하지 않은 메뉴 가격입니다.");
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
