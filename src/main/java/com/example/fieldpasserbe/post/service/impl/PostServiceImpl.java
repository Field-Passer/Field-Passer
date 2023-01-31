package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.service.PostService;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepositoryJPA boardRepository;

    @Override
    public Long countPostById(int id) {
        return boardRepository.countPostById(id);
    }
}
