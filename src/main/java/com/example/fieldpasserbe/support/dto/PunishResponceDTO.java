package com.example.fieldpasserbe.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PunishResponceDTO {
    private String punishId;
    private String adminName;
    private String memberName;
    private String reason;
    private Date judgeDate;
    private Date releaseDate;
}
