package com.example.fieldpasserbe.seok.service.impl;

import com.example.fieldpasserbe.seok.dto.PostListResponseDto;
import com.example.fieldpasserbe.seok.repository.PostRepository;
import com.example.fieldpasserbe.seok.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Slice<PostListResponseDto> PostList(int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 4, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findDefaultAll(pageRequest)
                .map(post -> new PostListResponseDto(post));
    }
}
