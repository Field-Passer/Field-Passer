package com.example.fieldpasserbe.post.controller;

import com.example.fieldpasserbe.post.dto.DistrictResponseDto;
import com.example.fieldpasserbe.post.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping("/api/district")
    public List<DistrictResponseDto> DistrictList() {
        return districtService.district();
    }
}
