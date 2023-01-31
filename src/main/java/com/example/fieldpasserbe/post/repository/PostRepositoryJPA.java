package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepositoryJPA extends JpaRepository<Post, Integer> {

    @Query("select count(b) from Post b where b.id = :id and b.deleteCheck = 0")
    Long countPostById(@Param("id") int id);
}
