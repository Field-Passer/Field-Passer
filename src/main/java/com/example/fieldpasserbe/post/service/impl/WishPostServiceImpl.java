package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.WishPostRequest;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import com.example.fieldpasserbe.post.repository.WishPostRepository;
import com.example.fieldpasserbe.post.service.WishPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WishPostServiceImpl implements WishPostService {

    private final WishPostRepository wishPostRepository;
    private final PostRepositoryJPA postRepositoryJPA;

    @Transactional
    @Override
    public String likePost(WishPostRequest wishPostRequest) {
        try {
            int postId = wishPostRequest.getPostId();
            postRepositoryJPA.updateWishCount(postId);
            wishPostRepository.save(wishPostRequest.toEntity());
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }
}
