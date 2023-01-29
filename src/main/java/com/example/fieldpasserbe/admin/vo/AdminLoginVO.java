package com.example.fieldpasserbe.admin.vo;

import com.example.fieldpasserbe.admin.dto.AdminLoginResponceDTO;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AdminLoginVO {

    private String resultCode;
    private AdminLoginResponceDTO resultData;
}
