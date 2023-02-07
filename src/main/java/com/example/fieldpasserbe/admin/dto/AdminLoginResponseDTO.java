package com.example.fieldpasserbe.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AdminLoginResponseDTO {

    private String email;
    private String profileImg;
    private String memberName;
}
