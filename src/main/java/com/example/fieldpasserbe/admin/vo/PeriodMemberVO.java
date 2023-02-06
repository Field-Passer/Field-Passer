package com.example.fieldpasserbe.admin.vo;

import com.example.fieldpasserbe.admin.dto.PeriodResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PeriodMemberVO {
    private String resultCode;
    private int resultDataNum;
    private List<PeriodResponseDTO> resultData;
}
