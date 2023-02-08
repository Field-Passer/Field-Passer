package com.example.fieldpasserbe.admin.vo;

import com.example.fieldpasserbe.admin.dto.AdminLoginResponseDTO;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AdminLoginVO {

    private String resultCode;
    private AdminLoginResponseDTO resultData;
}
