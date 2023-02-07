package com.example.fieldpasserbe.dto;

import com.example.fieldpasserbe.entity.MemberEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Login {

    private int id;
    private String email;
    private String password;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
        .id(id)
        .email(email)
        .password(password).build();
    }
}
