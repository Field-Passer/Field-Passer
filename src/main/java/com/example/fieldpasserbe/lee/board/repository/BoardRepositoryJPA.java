package com.example.fieldpasserbe.lee.board.repository;

import com.example.fieldpasserbe.lee.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepositoryJPA extends JpaRepository<Board, Integer> {

    @Query("select count(b) from Board b where b.id = :id and b.deleteCheck = 0")
    Long countPostById(@Param("id") int id);
}
