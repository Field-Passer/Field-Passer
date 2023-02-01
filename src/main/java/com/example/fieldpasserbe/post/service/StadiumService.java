package com.example.fieldpasserbe.post.service;

import com.example.fieldpasserbe.post.dto.StadiumResponseDto;

import java.util.List;

public interface StadiumService {
    List<StadiumResponseDto> stadium(String category, String district);
}
