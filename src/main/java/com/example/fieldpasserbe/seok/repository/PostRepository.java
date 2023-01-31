package com.example.fieldpasserbe.seok.repository;

import com.example.fieldpasserbe.seok.entity.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @EntityGraph(attributePaths = {"member","categoryList","districtList","stadiumList"})
    @Query("select p from Post p")
    Slice<Post> findDefaultAll(PageRequest pageRequest);
}
