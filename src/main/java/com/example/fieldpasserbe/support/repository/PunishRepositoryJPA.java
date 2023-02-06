package com.example.fieldpasserbe.support.repository;

import com.example.fieldpasserbe.support.dto.PunishDTO;
import com.example.fieldpasserbe.support.entity.Punish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

public interface PunishRepositoryJPA extends JpaRepository<Punish, Integer> {
    @Query("select count(p) from Punish p where p.target.memberId = :id")
    Long countBytargetId(@Param("id") int id);

    @Query("select p from Punish p where p.target.memberId = :id AND CURRENT_DATE between p.punishPeriod.judgeDate AND p.punishPeriod.releaseDate")
    Optional<Punish> findPunishBytargetMemberId(@Param("id") int id);

    @EntityGraph(attributePaths = {"report"})
    @Query("select p from Punish p where p.punishPeriod.releaseDate >= CURRENT_DATE")
    Page<Punish> findTotalPunish(PageRequest pageRequest);
}
