package com.example.fieldpasserbe.admin.vo;

import com.example.fieldpasserbe.admin.dto.PeriodMemberResponseDTO;
import com.example.fieldpasserbe.admin.dto.PeriodPostResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PeriodPostVO {
    private String resultCode;
    private Long resultDataNum;
    private List<PeriodPostResponseDTO> resultData;
    private int currentPage;
    private int totalPage;
    private Sort sort;
}
