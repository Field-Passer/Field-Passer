package com.example.fieldpasserbe.post.service;

import com.example.fieldpasserbe.post.dto.WishPostRequestDto;
import com.example.fieldpasserbe.post.dto.WishPostResponseDto;

import java.util.List;

public interface WishPostService {
    String likePost(WishPostRequestDto wishPostRequestDto);
    List<WishPostResponseDto> myLikePostList(int memberId);
}
