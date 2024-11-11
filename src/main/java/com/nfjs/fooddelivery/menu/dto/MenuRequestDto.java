package com.nfjs.fooddelivery.menu.dto;

import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.menu.enums.MenuStatus;
import lombok.Getter;

import java.util.UUID;

public record MenuRequestDto(
        UUID shopId,
        String menuName,
        int menuPrice,
        String menuImage,
        MenuStatus status) {


    public Menu toEntity() {
        return Menu.builder()
                .shopId(shopId)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuImage(menuImage)
                .status(status)
                .build();
    }
}
