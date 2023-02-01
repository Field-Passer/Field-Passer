package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.entity.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepositoryJPA extends JpaRepository<Post, Integer> {

    @Query("select count(p) from Post p where p.postId = :id and p.deleteCheck = 0")
    Long countPostById(@Param("id") int id);

    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    @Query("select p from Post p")
    Slice<Post> findDefaultAll(PageRequest pageRequest);

    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    Slice<Post> findByCategory_CategoryName(String category, PageRequest pageRequest);
}
