package com.example.fieldpasserbe.post.service;

import com.example.fieldpasserbe.post.dto.WishFieldRequestDto;
import com.example.fieldpasserbe.post.dto.WishFieldResponseDto;

import java.util.List;

public interface WishFieldService {
    String likeStadium(WishFieldRequestDto wishFieldRequestDto);
    List<WishFieldResponseDto> myLikeStadiums(int memberId);
}
