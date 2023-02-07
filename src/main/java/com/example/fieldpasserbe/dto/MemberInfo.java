package com.example.fieldpasserbe.dto;

import com.example.fieldpasserbe.entity.MemberEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfo {


    private int id;
    private String email;

    private String memberName;

    private String profile_img;

    public MemberInfo(MemberEntity memberEntity) {
        this.email = memberEntity.getEmail();
        this.memberName = memberEntity.getMemberName();
        this.profile_img = memberEntity.getProfile_img();
    }



    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .id(this.id)
                .email(this.email)
                .memberName(this.memberName)
                .profile_img(this.profile_img)
                .build();
    }
}
