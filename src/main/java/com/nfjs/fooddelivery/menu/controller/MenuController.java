package com.nfjs.fooddelivery.menu.controller;

import com.nfjs.fooddelivery.menu.dto.MenuAddRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.dto.MenuUpdateRequestDto;
import com.nfjs.fooddelivery.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/shops/{shopId}/menus")
    public ResponseEntity<MenuResponseDto> addMenu(@PathVariable UUID shopId, @RequestBody MenuAddRequestDto requestDto) {
        MenuResponseDto responseDto = menuService.addMenu(shopId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/menus/{menuId}")
    public ResponseEntity<MenuResponseDto> updateMenu(@PathVariable UUID menuId, @RequestBody MenuUpdateRequestDto requestDto) {
        MenuResponseDto responseDto = menuService.updateMenu(menuId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/menus/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable UUID menuId, @RequestParam Long userId) {
        menuService.deleteMenu(menuId, userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/shops/{shopId}/menus")
    public ResponseEntity<Page<MenuResponseDto>> getMenuList(@PathVariable UUID shopId,
                                                       @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
                                                       Pageable pageable) {

        Page<MenuResponseDto> menuList = menuService.getMenuList(shopId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(menuList);
    }
}
