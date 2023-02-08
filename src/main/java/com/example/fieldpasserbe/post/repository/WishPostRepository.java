package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.dto.WishPostId;
import com.example.fieldpasserbe.post.entity.WishPost;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishPostRepository extends JpaRepository<WishPost, WishPostId> {
    @EntityGraph(attributePaths = {"member","post"})
    @Query("select wp from WishPost wp where wp.member.memberId = :memberId AND wp.post.deleteCheck = 0 AND wp.post.blind = 0")
    List<WishPost> findByMemberId(@Param("memberId") int memberId);
}
