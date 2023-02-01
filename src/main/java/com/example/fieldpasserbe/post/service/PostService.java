package com.example.fieldpasserbe.post.service;

import com.example.fieldpasserbe.post.dto.PostListResponseDto;
import org.springframework.data.domain.Slice;

public interface PostService {

    Long countPostById(int id);

    Slice<PostListResponseDto> postList(int page);

    Slice<PostListResponseDto> postListByCategory(String category, int page);
}
