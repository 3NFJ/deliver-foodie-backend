package com.nfjs.fooddelivery.menu.dto;

import com.nfjs.fooddelivery.menu.enums.MenuStatus;

import java.util.UUID;

public record MenuUpdateRequestDto(
        UUID shopId,
        String menuName,
        int menuPrice,
        String menuImage,
        MenuStatus status) {
}
