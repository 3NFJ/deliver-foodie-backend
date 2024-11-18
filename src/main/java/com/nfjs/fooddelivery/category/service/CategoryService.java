package com.nfjs.fooddelivery.category.service;

import com.nfjs.fooddelivery.category.dto.CategoryCreateRequestDto;
import com.nfjs.fooddelivery.category.dto.CategoryCreateResponseDto;

public interface CategoryService {

    CategoryCreateResponseDto createCategory(CategoryCreateRequestDto categoryCreateRequestDto);
}
