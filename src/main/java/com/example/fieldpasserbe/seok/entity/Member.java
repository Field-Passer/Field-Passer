package com.example.fieldpasserbe.seok.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MEMBERNAME")
    private String memberName;

    @Column(name = "PROFILE_IMG")
    private String profileImg;

    @Column(name = "PRIVILEGE", columnDefinition = "TINYINT", length = 1)
    private int privilege;

    @Column(name = "AUTHORITY", columnDefinition = "TINYINT", length = 1)
    private int authority;

    @CreationTimestamp
    @Column(name = "SIGNUP_DATE")
    private LocalDateTime signupDate = LocalDateTime.now();

    @Column(name = "VISIT_COUNT")
    private int visitCount;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();
}
