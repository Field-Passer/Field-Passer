package com.example.fieldpasserbe.admin.dto;

import com.example.fieldpasserbe.support.dto.PunishDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberDTO {
    private int memberId;
    private String email;
    private String memberName;
    private String profileImg;
    private LocalDateTime signUpDate;
    private int visitCount;
    private Long postCount;
    private String privilege;
    private String authority;
    private PunishDTO punishDTO;
}
