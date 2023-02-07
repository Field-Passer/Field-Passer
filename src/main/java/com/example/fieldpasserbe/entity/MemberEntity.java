package com.example.fieldpasserbe.entity;


import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
//import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PASSWORD" ,nullable=false)

    private String password;

    @Column(name="EMAIL",nullable=false)
    private String email;

    @Column(name="MEMBERNAME",nullable=false)
    private String memberName;

    @Column(name="PROFILE_IMG",nullable=false)
    private String profile_img;

    @Column(name="PRIVILEGE", columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private byte privilege;

    @Column(name = "AUTHORITY",columnDefinition = "TINYINT(1) DEFAULT 0",length = 1)
    private byte authority;

    @CreationTimestamp
    @Column(name="SIGNUP_DATE",nullable=false)
    private LocalDateTime signup_date = LocalDateTime.now();

    @Column(name="VISIT_COUNT",nullable=false)
    private Integer visit_count;

    @Column(name = "DELETE_CHECK",columnDefinition = "TINYINT(1) DEFAULT 0",length = 1)
    private byte delete;

    @PrePersist
    public void prePersist(){
        this.visit_count = this.visit_count == null ? 0: this.visit_count;
    }


    /**
     * 비밀번호를 암호화
     * @param passwordEncoder 암호화 할 인코더 클래스
     * @return 변경된 유저 Entity
     */
    public MemberEntity hashPassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
        return this;
    }


    /**
     * 비밀번호 확인
     * @param plainPassword 암호화 이전의 비밀번호
     * @param passwordEncoder 암호화에 사용된 클래스
     * @return true | false
     */
    public boolean checkPasword(String plainPassword , PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.password);
    }


}
