package com.example.fieldpasserbe.admin.vo;

import com.example.fieldpasserbe.admin.dto.MemberDTO;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberVO {
    private String resultCode;
    private MemberDTO resultData;
}
