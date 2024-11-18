package com.nfjs.fooddelivery.category.dto;

import com.nfjs.fooddelivery.category.entity.Category;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CategoryCreateResponseDto {

    private UUID categoryId;
    private String categoryName;
    private Integer displayOrder;
    private LocalDateTime categoryCreatedAt;

    public CategoryCreateResponseDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.displayOrder = category.getDisplayOrder();
        this.categoryCreatedAt = category.getCreatedAt();
    }
}
