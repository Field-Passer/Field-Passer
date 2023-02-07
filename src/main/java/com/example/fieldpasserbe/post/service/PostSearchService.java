package com.example.fieldpasserbe.post.service;

import com.example.fieldpasserbe.admin.dto.PeriodMemberResponseDTO;
import com.example.fieldpasserbe.admin.dto.PeriodPostResponseDTO;
import com.example.fieldpasserbe.admin.dto.PostResponseDTO;
import com.example.fieldpasserbe.post.dto.PostListResponseDto;
import com.example.fieldpasserbe.post.dto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PostSearchService {

    Long countPostById(int id);

    void updateViewCount(int postId);

    PostResponseDto postDetailByPostId(int postId);

    Slice<PostListResponseDto> postList(int page);

    Slice<PostListResponseDto> postListByCategory(String category, int page);

    Slice<PostListResponseDto> postListByDistrict(String district, int page);

    Slice<PostListResponseDto> postListByCategoryAndDistrict(String category, String district, int page);

    Slice<PostListResponseDto> postListByCategoryAndDistrictAndStadium(String category, String district, String stadiumName, int page);

    List<PostListResponseDto> findImminent(String category);

    Page<PostResponseDTO> findPostsById(int page, int memberId) throws NullPointerException;

    Page<PostResponseDTO> lookupAllPosts(String startDate, String endDate, int page) throws Exception;

    Page<PeriodPostResponseDTO> checkNewposts(String startDate, String endDate, int page) throws Exception;
}
