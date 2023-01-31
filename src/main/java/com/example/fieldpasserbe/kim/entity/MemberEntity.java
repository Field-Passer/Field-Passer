package com.example.fieldpasserbe.kim.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


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
    private int id;

    @Column(name = "PASSWORD" ,nullable=false)
    private String password;

    @Column(name="EMAIL",nullable=false)
    private String email;

    @Column(name="MEMBERNAME",nullable=false)
    private String memberName;

    @Column(name="PROFILE_IMG",nullable=false)
    private String profile_img;

    @Column(name="PRIVILEGE", columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private int privilege;

    @Column(name = "AUTHORITY",columnDefinition = "TINYINT(1) DEFAULT 0",length = 1)
    private int authority;

    @CreationTimestamp
    @Column(name="SIGNUP_DATE",nullable=false)
    private LocalDateTime signup_date = LocalDateTime.now();

    @Column(name="VISIT_COUNT",nullable=false)
    private Integer visit_count;

    @PrePersist
    public void prePersist(){
        this.visit_count = this.visit_count == null ? 0: this.visit_count;
    }


}

