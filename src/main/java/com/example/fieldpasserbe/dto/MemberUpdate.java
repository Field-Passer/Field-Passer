package com.example.fieldpasserbe.dto;


import com.example.fieldpasserbe.entity.MemberEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberUpdate {

    private int id;
    private String email;
    private String password;
    private String memberName;
    private String profile_img;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .profile_img(this.profile_img)
                .memberName(this.memberName)
                .build();
    }


}
