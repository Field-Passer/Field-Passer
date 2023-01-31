package com.example.fieldpasserbe.lee.admin.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class MemberListDTO {
    private String email;
    private String memberName;
    private LocalDateTime signupDate;
    private Long postCount;
    private int visitCount;
    private String privilege;
    private Long reportNum;
    private String authority;
}
