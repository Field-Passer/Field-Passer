package com.example.fieldpasserbe.lee.admin.vo;

import com.example.fieldpasserbe.lee.admin.dto.MemberListDTO;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberListVO {
    private String resultCode;
    private Long resultDataNum;
    private List<MemberListDTO> resultData;
    private int currentPage;
    private int totalPage;
    private Sort sort;
}
