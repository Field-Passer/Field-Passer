package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.entity.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepositoryJPA extends JpaRepository<Post, Integer> {

    @Query("select count(p) from Post p where p.postId = :id and p.deleteCheck = 0")
    Long countPostById(@Param("id") int id);

    @Modifying
    @Query("update Post p set p.viewCount = p.viewCount + 1 where p.postId = :postId")
    void updateViewCount(@Param("postId") int postId);

    @Modifying
    @Query("update Post p set p.wishCount = p.wishCount + 1 where p.postId = :postId")
    void updateWishCount(@Param("postId") int postId);

    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    Optional<Post> findByPostId(int postId);

    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    @Query("select p from Post p")
    Slice<Post> findDefaultAll(PageRequest pageRequest);

    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    Slice<Post> findByCategory_CategoryName(String category, PageRequest pageRequest);

    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    Slice<Post> findByDistrict_DistrictName(String district, PageRequest pageRequest);

    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    Slice<Post> findByCategory_CategoryNameAndDistrict_DistrictName(String category, String district, PageRequest pageRequest);

    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    Slice<Post> findByCategory_CategoryNameAndDistrict_DistrictNameAndStadium_StadiumName(String category,
                                                                                              String district,
                                                                                              String stadiumName,
                                                                                              PageRequest pageRequest);

    List<Post> findByStartTimeBefore(LocalDateTime dateTime);
}
