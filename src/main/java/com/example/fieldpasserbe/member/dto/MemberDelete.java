package com.example.fieldpasserbe.member.dto;

import com.example.fieldpasserbe.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDelete {

   private int memberId;

   private byte delete;


   public MemberDelete(Member member){
       this.delete = member.getDelete();
   }
}
