package com.nfjs.fooddelivery.menu.controller;

import com.nfjs.fooddelivery.menu.dto.MenuRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<MenuResponseDto> addMenu(@PathVariable UUID shopId, @RequestBody MenuRequestDto requestDto) {
        MenuResponseDto responseDto = menuService.addMenu(shopId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
