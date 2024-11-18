package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.menu.dto.MenuAddRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.dto.MenuUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nfjs.fooddelivery.user.entity.User;

import java.util.UUID;

public interface MenuService {

    MenuResponseDto addMenu(UUID shopId, MenuAddRequestDto requestDto, User userDetails);
    void deleteMenu(UUID menuId, User userId);
    Page<MenuResponseDto> getMenuList(UUID shopId, Pageable pageable);
    MenuResponseDto updateMenu(UUID menuId, MenuUpdateRequestDto requestDto, User user);
}
