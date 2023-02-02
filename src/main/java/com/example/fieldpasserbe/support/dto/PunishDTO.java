package com.example.fieldpasserbe.support.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PunishDTO {
    private boolean punishResult;
    private int punishId;
    private Date judgeDate;
    private Date releaseDate;
}
