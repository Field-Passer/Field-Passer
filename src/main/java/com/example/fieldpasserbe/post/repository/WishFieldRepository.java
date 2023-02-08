package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.entity.WishField;
import com.example.fieldpasserbe.post.entity.WishPost;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishFieldRepository extends JpaRepository<WishField, WishField> {
    @EntityGraph(attributePaths = {"member","stadium"})
    List<WishField> findByMember_MemberId(int memberId);
}
