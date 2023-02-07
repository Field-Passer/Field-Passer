package com.example.fieldpasserbe.support.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PunishResponseDTO {
    private int punishId;
    private String adminName;
    private String memberName;
    private String reason;
    private String judgeDate;
    private String releaseDate;
}
