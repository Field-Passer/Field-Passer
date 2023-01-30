package com.example.fieldpasserbe.lee.admin.vo;

import com.example.fieldpasserbe.lee.admin.dto.MemberListDTO;
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
