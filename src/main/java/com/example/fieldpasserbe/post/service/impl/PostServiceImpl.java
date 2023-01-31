package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.service.PostService;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import com.example.fieldpasserbe.post.dto.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepositoryJPA postRepository;

    @Override
    public Long countPostById(int id) {
        return postRepository.countPostById(id);
    }

    @Override
    public Slice<PostListResponseDto> PostList(int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 4, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findDefaultAll(pageRequest)
                .map(post -> new PostListResponseDto(post));
    }
}
