package com.example.fieldpasserbe.seok.repository;

import com.example.fieldpasserbe.seok.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
