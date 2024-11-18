package com.nfjs.fooddelivery.category.service;

import com.nfjs.fooddelivery.category.dto.CategoryCreateRequestDto;
import com.nfjs.fooddelivery.category.dto.CategoryCreateResponseDto;
import com.nfjs.fooddelivery.category.entity.Category;
import com.nfjs.fooddelivery.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryCreateResponseDto createCategory(CategoryCreateRequestDto categoryCreateRequestDto) {

        log.info("카테고리 등록 서비스 호출 : START");
        Category category = new Category(categoryCreateRequestDto);
        categoryRepository.save(category);

        log.info("카테고리 등록 서비스 호출 : END");
        return new CategoryCreateResponseDto(category);
    }
}