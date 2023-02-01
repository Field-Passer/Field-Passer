package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.post.entity.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryResponseDto {

    private int categoryId;
    private String category;

    public CategoryResponseDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.category = category.getCategoryName();
    }

}
