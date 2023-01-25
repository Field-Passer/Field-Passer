package com.example.fieldpasserbe.admin.entity;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "MEMBER")
public class Member {

    enum Privilege {
        일반_회원, 관리자
    }

    enum Authority {
        이메일_인증_전, 인증_완료
    }

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "password")
    private String password;

    @Column(name = "MEMBERNAME")
    private String memberName;

    @Column(name = "PROFILE_IMG")
    private String profileIMG;

    @Column(name = "PRIVILEGE")
    @Enumerated(EnumType.ORDINAL)
    private Privilege privilege;

    @Column(name = "AUTHORITY")
    @Enumerated(EnumType.ORDINAL)
    private Authority authority;

    @Column(name = "SIGNUP_DATE")
    private String signUpDate;

    @Column(name = "VISIT_COUNT")
    private int visitCount;
}