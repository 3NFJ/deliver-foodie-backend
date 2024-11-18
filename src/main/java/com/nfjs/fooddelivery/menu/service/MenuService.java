package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.menu.dto.MenuAddRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.dto.MenuUpdateRequestDto;
import com.nfjs.fooddelivery.user.entity.User;

import java.util.UUID;

public interface MenuService {

    MenuResponseDto addMenu(UUID shopId, MenuAddRequestDto requestDto, User userDetails);

    MenuResponseDto updateMenu(UUID menuId, MenuUpdateRequestDto requestDto, User user);
}
