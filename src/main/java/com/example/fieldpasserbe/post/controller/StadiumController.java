package com.example.fieldpasserbe.post.controller;

import com.example.fieldpasserbe.post.dto.StadiumResponseDto;
import com.example.fieldpasserbe.post.dto.WishFieldRequestDto;
import com.example.fieldpasserbe.post.dto.WishFieldResponseDto;
import com.example.fieldpasserbe.post.dto.WishPostResponseDto;
import com.example.fieldpasserbe.post.service.StadiumService;
import com.example.fieldpasserbe.post.service.WishFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StadiumController {

    private final StadiumService stadiumService;
    private final WishFieldService wishFieldService;

    @GetMapping("/api/stadiumList")
    public List<StadiumResponseDto> StadiumList(@RequestParam(name = "category") String category,
                                                @RequestParam(name = "district") String district) {
        return stadiumService.stadium(category, district);
    }

    @PostMapping("/api/like/stadium")
    public String likeStadium(WishFieldRequestDto wishFieldRequestDto) {
        return wishFieldService.likeStadium(wishFieldRequestDto);
    }

    @GetMapping("/api/myPage/like/stadium") //추후 세션으로 변경
    public List<WishFieldResponseDto> myLikePostList(@RequestParam(name = "memberId") int memberId) {
        return wishFieldService.myLikeStadiums(memberId);
    }
}
