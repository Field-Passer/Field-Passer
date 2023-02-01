package com.example.fieldpasserbe.post.service;

import com.example.fieldpasserbe.post.dto.PostListResponseDto;
import org.springframework.data.domain.Slice;

public interface PostService {

    Long countPostById(int id);

    Slice<PostListResponseDto> postList(int page);

    Slice<PostListResponseDto> postListByCategory(String category, int page);

    Slice<PostListResponseDto> postListByDistrict(String district, int page);

    Slice<PostListResponseDto> postListByCategoryAndDistrict(String category, String district, int page);

    Slice<PostListResponseDto> postListByCategoryAndDistrictAndStadium(String category, String district, String stadiumName, int page);

}
