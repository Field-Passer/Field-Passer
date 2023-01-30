package com.example.fieldpasserbe.lee.board.service.impl;

import com.example.fieldpasserbe.lee.board.repository.BoardRepositoryJPA;
import com.example.fieldpasserbe.lee.board.service.BoardService;
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
