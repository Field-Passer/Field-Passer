package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.CategoryResponseDto;
import com.example.fieldpasserbe.post.repository.CategoryRepositoryJPA;
import com.example.fieldpasserbe.post.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepositoryJPA categoryRepository;
    @Override
    public List<CategoryResponseDto> category() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryResponseDto(category))
                .collect(Collectors.toList());
    }
}
