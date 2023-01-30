package com.example.fieldpasserbe.lee.admin.vo;

import com.example.fieldpasserbe.lee.admin.dto.AdminLoginResponceDTO;
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
