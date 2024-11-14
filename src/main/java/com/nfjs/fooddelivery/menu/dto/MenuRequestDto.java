package com.nfjs.fooddelivery.menu.dto;

import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.enums.MenuStatus;
import com.nfjs.fooddelivery.shop.entitiy.Shop;

public record MenuRequestDto(
        UUID shopId,
        String menuName,
        int menuPrice,
        String menuImage,
        MenuStatus status) {


    public Menu toEntity(Shop shop) {
        return Menu.builder()
                .shop(shop)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuImage(menuImage)
                .status(status)
                .build();
    }
}
