package com.nfjs.fooddelivery.menu.dto;

import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.enums.MenuStatus;

import java.util.UUID;

public record MenuResponseDto(
        UUID shopId,
        String menuName,
        int menuPrice,
        String menuImage,
        MenuStatus status) {

    public static MenuResponseDto from(Menu menu) {
        return new MenuResponseDto(
                menu.getShop().getShopId(),
                menu.getMenuName(),
                menu.getMenuPrice(),
                menu.getMenuImage(),
                menu.getStatus()
        );
    }
}
