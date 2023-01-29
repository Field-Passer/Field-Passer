package com.example.fieldpasserbe.admin.vo;

import com.example.fieldpasserbe.admin.dto.MemberListDTO;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberListVO {
    private String resultCode;
    private int resultDataNum;
    List<MemberListDTO> resultData;
}
