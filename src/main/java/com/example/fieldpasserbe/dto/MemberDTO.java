package com.example.fieldpasserbe.dto;

import com.example.fieldpasserbe.entity.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private int id;
    private String password;
    private String email;
    private String memberName;
    private String profile_img;
    private byte privilege;
    private byte authority;

    private int visit_count;

    private byte delete;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .id(id)
                .password(this.password)
                .email(this.email)
                .memberName(this.memberName)
                .profile_img(this.profile_img)
                .privilege(this.privilege)
                .authority(this.authority)
                .visit_count(this.visit_count)
                .delete(this.delete)
                .build();

    }


}
