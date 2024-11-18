package com.nfjs.fooddelivery.menu.controller;

import com.nfjs.fooddelivery.menu.dto.MenuAddRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuResponseDto;
import com.nfjs.fooddelivery.menu.dto.MenuUpdateRequestDto;
import com.nfjs.fooddelivery.menu.service.MenuService;
import com.nfjs.fooddelivery.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/shops/{shopId}/menus")
    public ResponseEntity<MenuResponseDto> addMenu(@PathVariable UUID shopId, @RequestBody MenuAddRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MenuResponseDto responseDto = menuService.addMenu(shopId, requestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/menus/{menuId}")
    public ResponseEntity<MenuResponseDto> updateMenu(@PathVariable UUID menuId, @RequestBody MenuUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MenuResponseDto responseDto = menuService.updateMenu(menuId, requestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
