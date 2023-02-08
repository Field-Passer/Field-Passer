package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.WishPostRequestDto;
import com.example.fieldpasserbe.post.dto.WishPostResponseDto;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import com.example.fieldpasserbe.post.repository.WishPostRepository;
import com.example.fieldpasserbe.post.service.WishPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WishPostServiceImpl implements WishPostService {

    private final WishPostRepository wishPostRepository;
    private final PostRepositoryJPA postRepositoryJPA;

    @Transactional
    @Override
    public String likePost(WishPostRequestDto wishPostRequestDto) {
        try {
            int postId = wishPostRequestDto.getPostId();
            postRepositoryJPA.updateWishCount(postId);
            wishPostRepository.save(wishPostRequestDto.toEntity());
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }

    @Override
    public List<WishPostResponseDto> myLikePostList(int memberId) {
        return wishPostRepository.findByMemberId(memberId)
                .stream()
                .map(wishPost -> new WishPostResponseDto(wishPost))
                .collect(Collectors.toList());
    }
}
