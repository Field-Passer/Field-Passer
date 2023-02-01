package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.post.entity.Stadium;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StadiumResponseDto {
    private int stadiumId;
    private int categoryId;
    private int districtId;
    private String stadiumName;
    private String latitude;
    private String longitude;
    private String phone;
    private String defaultImageUrl;

    public StadiumResponseDto (Stadium stadium) {
        this.stadiumId = stadium.getStadiumId();
        this.categoryId = stadium.getCategory().getCategoryId();
        this.districtId = stadium.getDistrict().getDistrictId();
        this.stadiumName = stadium.getStadiumName();
        this.latitude = stadium.getLatitude();
        this.longitude = stadium.getLongitude();
        this.phone = stadium.getPhone();
        this.defaultImageUrl = stadium.getDefaultImageUrl();
    }
}
