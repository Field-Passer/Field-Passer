package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.WishFieldRequest;
import com.example.fieldpasserbe.post.repository.WishFieldRepository;
import com.example.fieldpasserbe.post.service.WishFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WishFieldServiceImpl implements WishFieldService {

    private final WishFieldRepository wishFieldRepository;

    @Override
    public String likeStadium(WishFieldRequest wishFieldRequest) {
        try {
            wishFieldRepository.save(wishFieldRequest.toEntity());
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }
}
