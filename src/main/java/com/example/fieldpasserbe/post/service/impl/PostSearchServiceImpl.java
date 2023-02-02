package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.dto.PostResponseDto;
import com.example.fieldpasserbe.post.service.PostSearchService;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import com.example.fieldpasserbe.post.dto.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostSearchServiceImpl implements PostSearchService {

    private final PostRepositoryJPA postRepository;

    @Override
    public Long countPostById(int id) {
        return postRepository.countPostById(id);
    }

    @Transactional
    @Override
    public void updateViewCount(int postId) {
        postRepository.updateViewCount(postId);
    }

    @Override
    public PostResponseDto postDetailByPostId(int postId) {
        return postRepository.findByPostId(postId).map(post -> new PostResponseDto(post)).get();
    }

    @Override
    public Slice<PostListResponseDto> postList(int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findDefaultAll(pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public Slice<PostListResponseDto> postListByCategory(String category, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));
        return postRepository.findByCategory_CategoryName(category, pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public Slice<PostListResponseDto> postListByDistrict(String district, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findByDistrict_DistrictName(district, pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public Slice<PostListResponseDto> postListByCategoryAndDistrict(String category, String district, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findByCategory_CategoryNameAndDistrict_DistrictName(category, district, pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public Slice<PostListResponseDto> postListByCategoryAndDistrictAndStadium(String category, String district, String stadiumName, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findByCategory_CategoryNameAndDistrict_DistrictNameAndStadium_StadiumName(category, district, stadiumName, pageRequest)
                .map(post -> new PostListResponseDto(post));
    }
}
