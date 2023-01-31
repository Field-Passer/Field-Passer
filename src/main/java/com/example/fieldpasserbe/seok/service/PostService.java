package com.example.fieldpasserbe.seok.service;

import com.example.fieldpasserbe.seok.dto.PostListResponseDto;
import org.springframework.data.domain.Slice;

public interface PostService {
    Slice<PostListResponseDto> PostList(int page);
}
