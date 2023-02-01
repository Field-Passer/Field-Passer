package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryJPA extends JpaRepository<Category, Integer> {
}
