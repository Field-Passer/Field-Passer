package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.WishFieldRequestDto;
import com.example.fieldpasserbe.post.dto.WishFieldResponseDto;
import com.example.fieldpasserbe.post.repository.WishFieldRepository;
import com.example.fieldpasserbe.post.service.WishFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WishFieldServiceImpl implements WishFieldService {

    private final WishFieldRepository wishFieldRepository;

    @Override
    public String likeStadium(WishFieldRequestDto wishFieldRequestDto) {
        try {
            wishFieldRepository.save(wishFieldRequestDto.toEntity());
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }

    @Override
    public List<WishFieldResponseDto> myLikeStadiums(int memberId) {
        return wishFieldRepository.findByMember_MemberId(memberId)
                .stream()
                .map(wishField -> new WishFieldResponseDto(wishField))
                .collect(Collectors.toList());
    }
}
