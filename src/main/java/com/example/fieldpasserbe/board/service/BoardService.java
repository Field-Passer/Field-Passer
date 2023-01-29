package com.example.fieldpasserbe.board.service;

import com.example.fieldpasserbe.board.entity.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardService {

    Long countPostById(int id);
}
