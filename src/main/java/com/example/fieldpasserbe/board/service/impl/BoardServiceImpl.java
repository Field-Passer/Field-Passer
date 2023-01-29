package com.example.fieldpasserbe.board.service.impl;

import com.example.fieldpasserbe.board.repository.BoardRepositoryJPA;
import com.example.fieldpasserbe.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepositoryJPA boardRepository;

    @Override
    public Long countPostById(int id) {
        return boardRepository.countPostById(id);
    }
}
