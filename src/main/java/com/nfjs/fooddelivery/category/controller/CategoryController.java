package com.nfjs.fooddelivery.category.controller;

import com.nfjs.fooddelivery.category.dto.CategoryCreateRequestDto;
import com.nfjs.fooddelivery.category.dto.CategoryCreateResponseDto;
import com.nfjs.fooddelivery.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<CategoryCreateResponseDto> createCategory(
            @RequestBody CategoryCreateRequestDto categoryCreateRequestDto) {

        log.info("카테고리 등록 URL 맵핑 : OK");
        CategoryCreateResponseDto responseDto = categoryService.createCategory(categoryCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
