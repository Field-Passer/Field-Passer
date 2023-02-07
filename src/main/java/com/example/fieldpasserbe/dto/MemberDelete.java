package com.example.fieldpasserbe.dto;

import com.example.fieldpasserbe.entity.MemberEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDelete {

    private int id;
    private byte delete;


    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .id(id)
                .delete(delete)
                .build();
    }

}
