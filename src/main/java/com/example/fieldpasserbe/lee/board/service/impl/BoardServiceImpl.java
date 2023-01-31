package com.example.fieldpasserbe.lee.board.service.impl;

import com.example.fieldpasserbe.lee.board.repository.BoardRepositoryJPA;
import com.example.fieldpasserbe.lee.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepositoryJPA boardRepository;

    @Override
    public Long countPostById(int id) {
        return boardRepository.countPostById(id);
    }
}
