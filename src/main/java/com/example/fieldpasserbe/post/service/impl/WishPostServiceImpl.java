package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.WishPostRequest;
import com.example.fieldpasserbe.post.repository.WishPostRepository;
import com.example.fieldpasserbe.post.service.WishPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WishPostServiceImpl implements WishPostService {

    private final WishPostRepository wishPostRepository;

    @Override
    public String likePost(WishPostRequest wishPostRequest) {
        try {
            wishPostRepository.save(wishPostRequest.toEntity());
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }
}
