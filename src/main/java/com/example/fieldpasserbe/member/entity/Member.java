package com.example.fieldpasserbe.member.entity;

import com.example.fieldpasserbe.admin.entity.Admin;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Integer id;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date signUpDate;

    @Column(name = "VISIT_COUNT")
    private Integer visitCount;

    @OneToOne(mappedBy = "member")
    private Admin admin;
}