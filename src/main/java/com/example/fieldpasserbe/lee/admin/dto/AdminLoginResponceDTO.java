package com.example.fieldpasserbe.lee.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AdminLoginResponceDTO {

    private String email;
    private String profileImg;
    private String memberName;
}
