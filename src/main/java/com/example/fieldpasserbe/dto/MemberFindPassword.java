package com.example.fieldpasserbe.dto;


import com.example.fieldpasserbe.entity.MemberEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberFindPassword {

    private String email;


    public MemberEntity toEntity(){

        return MemberEntity.builder()
                .email(this.email)
                .build();
    }
}
