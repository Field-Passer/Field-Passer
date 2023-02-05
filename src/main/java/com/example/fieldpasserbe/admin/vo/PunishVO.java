package com.example.fieldpasserbe.admin.vo;

import com.example.fieldpasserbe.support.dto.PunishResponceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PunishVO {
    private String resultCode;
    private Long resultDataNum;
    private List<PunishResponceDTO> resultData;
}
