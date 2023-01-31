package com.example.fieldpasserbe.board.service.impl;

import com.example.fieldpasserbe.board.service.BoardService;
import com.example.fieldpasserbe.board.repository.BoardRepositoryJPA;
import lombok.RequiredArgsConstructor;
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
