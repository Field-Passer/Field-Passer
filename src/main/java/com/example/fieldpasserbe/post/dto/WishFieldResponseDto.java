package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.post.entity.WishField;
import com.example.fieldpasserbe.post.entity.WishPost;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishFieldResponseDto {
    private int stadiumId;
    private String stadiumName;

    public WishFieldResponseDto(WishField wishField) {
        this.stadiumId = wishField.getStadium().getStadiumId();
        this.stadiumName = wishField.getStadium().getStadiumName();
    }
}
