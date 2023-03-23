package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.admin.dto.PeriodPostResponseDTO;
import com.example.fieldpasserbe.admin.dto.PostResponseDTO;
import com.example.fieldpasserbe.post.entity.Post;
import org.springframework.data.domain.Page;
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

    @Modifying
    @Query("update Post p set p.blind = 1 where p.startTime < :dateTime")
    void updateTimeOverPost(@Param("dateTime") LocalDateTime dateTime);

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
    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    List<Post> findByStartTimeBefore(LocalDateTime dateTime);
    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    List<Post> findTop20ByCategory_CategoryNameAndStartTimeAfterOrderByStartTimeAsc(String category, LocalDateTime dateTime);
    @EntityGraph(attributePaths = {"member","category","district","stadium"})
    List<Post> findByStadium_StadiumNameOrderByRegisterDateDesc(String stadiumName);

    //@EntityGraph(attributePaths = {"member","category","district","stadium"})
    @Query(value = "SELECT p.post_id as postId," +
            "p.id as memberId," +
            "m.membername as memberName," +
            "c.category as category," +
            "d.district as district," +
            "s.stadium_name as stadiumName," +
            "p.REGISTER_DATE as registerDate," +
            "p.START_TIME as startTime," +
            "p.END_TIME as endTime," +
            "p.TITLE as title," +
            "p.PRICE as price," +
            "p.TRANSACTION_STATUS as transactionStatus," +
            "p.blind as blind " +
            "FROM fieldpasser.post as p " +
            "left join fieldpasser.stadium_list as s " +
            "on s.stadium_id = p.stadium_id " +
            "left join fieldpasser.district_list as d " +
            "on d.district_id = p.district_id " +
            "left join fieldpasser.category_list as c " +
            "on c.category_id = p.category_id " +
            "left join fieldpasser.member as m " +
            "on m.id = p.id " +
            "where p.id = :memberId " +
            "and p.DELETE_CHECK = 0", nativeQuery = true)
    Page<PostResponseDTO> findPostsByMemberId(@Param("memberId") int memberId, PageRequest pageRequest);

    @Query(value = "SELECT p.post_id as postId, " +
            "p.id as memberId, " +
            "m.membername as memberName, " +
            "c.category as category, " +
            "d.district as district, " +
            "s.stadium_name as stadiumName, " +
            "p.REGISTER_DATE as registerDate, " +
            "p.START_TIME as startTime, " +
            "p.END_TIME as endTime, " +
            "p.TITLE as title, " +
            "p.PRICE as price, " +
            "p.TRANSACTION_STATUS as transactionStatus, " +
            "p.blind as blind " +
            "FROM fieldpasser.post as p " +
            "left join fieldpasser.stadium_list as s " +
            "on s.stadium_id = p.stadium_id " +
            "left join fieldpasser.district_list as d " +
            "on d.district_id = p.district_id " +
            "left join fieldpasser.category_list as c " +
            "on c.category_id = p.category_id " +
            "left join fieldpasser.member as m " +
            "on m.id = p.id " +
            "where p.DELETE_CHECK = 0 " +
            "AND Date_Format(p.register_Date, '%Y-%m-%d') between :startDate AND :endDate",
            countQuery = "select count(p.post_id) " +
            "FROM fieldpasser.post as p " +
            "left join fieldpasser.stadium_list as s " +
            "on s.stadium_id = p.stadium_id " +
            "left join fieldpasser.district_list as d " +
            "on d.district_id = p.district_id " +
            "left join fieldpasser.category_list as c " +
            "on c.category_id = p.category_id " +
            "left join fieldpasser.member as m " +
            "on m.id = p.id " +
            "where p.DELETE_CHECK = 0 " +
            "AND Date_Format(p.register_Date, '%Y-%m-%d') between :startDate AND :endDate", nativeQuery = true)
    Page<PostResponseDTO> findTotalPosts(@Param("startDate") String startDate, @Param("endDate") String endDate, PageRequest pageRequest);

    @Query(value = "select Date_Format(p.register_Date, '%Y-%m-%d') as date, count(p.post_id) as postNum from fieldpasser.post as p where Date_Format(p.register_Date, '%Y-%m-%d') between :startDate AND :endDate AND p.DELETE_CHECK = 0 GROUP BY Date_Format(p.register_Date, '%Y-%m-%d') order by date asc",
            countQuery = "select count(*) as count " +
                    "from(" +
                    "select Date_Format(p.register_Date, '%Y-%m-%d') as date, count(p.post_id) as postNum from fieldpasser.post as p where Date_Format(p.register_Date, '%Y-%m-%d') between :startDate AND :endDate AND p.DELETE_CHECK = 0 GROUP BY Date_Format(p.register_Date, '%Y-%m-%d')" +
                    ") as c", nativeQuery = true)
    Page<PeriodPostResponseDTO> findNewPosts(String startDate, String endDate, PageRequest pageRequest);

    @Query(value = "select * from fieldpasser.post as p where p.post_id = :postId", nativeQuery = true)
    Optional<Post> findByPostIdIncludeBlind(@Param("postId") int postId);

}
