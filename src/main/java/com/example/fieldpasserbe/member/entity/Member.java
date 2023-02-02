package com.example.fieldpasserbe.member.entity;

import com.example.fieldpasserbe.admin.entity.Admin;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer memberId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MEMBERNAME")
    private String memberName;

    @Column(name = "PROFILE_IMG")
    private String profileImg;
    @Column(name = "PRIVILEGE")
    private byte privilege;

    @Column(name = "AUTHORITY")
    private byte authority;

    @Column(name = "SIGNUP_DATE")
    private LocalDateTime signUpDate;

    @Column(name = "VISIT_COUNT")
    private Integer visitCount;

    @Column(name = "DELETE_CHECK")
    private byte delete;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Admin admin;


    public String convertPrivilege() {
        if (this.privilege == 0) {
            return "일반 회원";
        } else {
            return "관리자";
        }
    }

    public String convertAuthority() {
        if (this.authority == 0) {
            return "이메일 인증 전";
        } else {
            return "인증 완료";
        }
    }

    public void promote() {
        this.privilege = 1;
    }
}