package com.nfjs.fooddelivery.menu.service;

import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MenuService {

    MenuResponseDto addMenu(UUID shopId, MenuRequestDto requestDto);

    MenuResponseDto updateMenu(UUID menuId, MenuRequestDto requestDto);

    void deleteMenu(UUID menuId, Long userId);

    List<MenuResponseDto> getMenuList(UUID shopId, Pageable pageable);
}
