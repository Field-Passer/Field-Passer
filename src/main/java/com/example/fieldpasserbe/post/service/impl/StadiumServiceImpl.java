package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.StadiumResponseDto;
import com.example.fieldpasserbe.post.repository.StadiumRepositoryJPA;
import com.example.fieldpasserbe.post.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepositoryJPA stadiumRepository;

    @Override
    public List<StadiumResponseDto> stadium(String category, String district) {
        return stadiumRepository.findByCategory_CategoryNameAndDistrict_DistrictName(category, district)
                .stream()
                .map(stadium -> new StadiumResponseDto(stadium))
                .collect(Collectors.toList());
    }
}
