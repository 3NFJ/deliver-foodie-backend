package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;

import java.util.UUID;

public interface MenuService {

    MenuResponseDto addMenu(UUID shopId, MenuRequestDto requestDto);

    MenuResponseDto updateMenu(UUID menuId, MenuRequestDto requestDto);
}
