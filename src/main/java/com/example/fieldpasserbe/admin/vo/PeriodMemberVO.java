package com.example.fieldpasserbe.admin.vo;

import com.example.fieldpasserbe.admin.dto.PeriodMemberResponseDTO;
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
public class PeriodMemberVO {
    private String resultCode;
    private int resultDataNum;
    private List<PeriodMemberResponseDTO> resultData;
    private int currentPage;
    private int totalPage;
    private Sort sort;
}
