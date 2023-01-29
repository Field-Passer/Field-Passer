package com.example.fieldpasserbe.admin.dto;

import com.example.fieldpasserbe.admin.entity.Admin;
import com.example.fieldpasserbe.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AdminLoginRequestDTO {
    private String email;
    private String password;
}
