package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepositoryJPA extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryName(String categoryName);
}
