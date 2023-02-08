package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.dto.WishPostId;
import com.example.fieldpasserbe.post.entity.WishPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishPostRepository extends JpaRepository<WishPost, WishPostId> {
}
