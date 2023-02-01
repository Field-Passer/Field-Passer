package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.DistrictResponseDto;
import com.example.fieldpasserbe.post.repository.DistrictRepositoryJPA;
import com.example.fieldpasserbe.post.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepositoryJPA districtRepository;
    @Override
    public List<DistrictResponseDto> district() {
        return districtRepository.findAll()
                .stream()
                .map(district -> new DistrictResponseDto(district))
                .collect(Collectors.toList());
    }
}
