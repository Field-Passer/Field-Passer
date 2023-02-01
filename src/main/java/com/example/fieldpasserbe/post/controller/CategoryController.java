package com.example.fieldpasserbe.post.controller;

import com.example.fieldpasserbe.post.dto.CategoryResponseDto;
import com.example.fieldpasserbe.post.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/category") //카테고리 리스트 조회
    public List<CategoryResponseDto> CategoryList() {
        return categoryService.category();
    }
}
