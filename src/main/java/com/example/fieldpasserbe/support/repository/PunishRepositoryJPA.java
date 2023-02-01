package com.example.fieldpasserbe.support.repository;

import com.example.fieldpasserbe.support.entity.Punish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PunishRepositoryJPA extends JpaRepository<Punish, Integer> {
    @Query("select count(p) from Punish p where p.target.id = :id")
    Long countBytargetId(@Param("id") int id);
}
