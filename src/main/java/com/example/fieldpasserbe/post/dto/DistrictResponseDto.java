package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.post.entity.District;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DistrictResponseDto {
    private int districtId;
    private String district;

    public DistrictResponseDto(District district) {
        this.districtId = district.getDistrictId();
        this.district = district.getDistrictName();
    }
}
