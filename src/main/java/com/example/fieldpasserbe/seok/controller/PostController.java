package com.example.fieldpasserbe.seok.controller;

import com.example.fieldpasserbe.seok.dto.PostListResponseDto;
import com.example.fieldpasserbe.seok.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/api/post")
    public Slice<PostListResponseDto> postList(@RequestParam(name = "page") int page) {
        return postService.PostList(page);
    }
}
