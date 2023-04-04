package com.example.fieldpasserbe.member.entity;

import com.example.fieldpasserbe.admin.entity.Admin;
import com.example.fieldpasserbe.chat.entity.ChatMessage;
import com.example.fieldpasserbe.chat.entity.ChatRoom;
import com.example.fieldpasserbe.support.entity.Question;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer memberId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MEMBERNAME")
    private String memberName;

    @Column(name = "PROFILE_IMG",nullable = false)
    private String profileImg;
    @Column(name = "PRIVILEGE",columnDefinition = "TINYINT(1) DEFAULT 0",length = 1)
    private int privilege;

    @Column(name = "AUTHORITY",columnDefinition = "TINYINT(1) DEFAULT 0",length = 1)
    private int authority;

    @CreationTimestamp
    @Column(name = "SIGNUP_DATE", nullable = false)
    private LocalDateTime signUpDate;

    @Column(name = "VISIT_COUNT")
    private Integer visitCount;

    @Column(name = "DELETE_CHECK",columnDefinition = "TINYINT(1) DEFAULT 0",length = 1)
    private byte delete;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Admin admin;

    @OneToMany(mappedBy = "seller")
    private List<ChatRoom> sellerChatRooms;

    @OneToMany(mappedBy = "buyer")
    private List<ChatRoom> buyerChatRooms;

    @OneToMany(mappedBy = "member")
    private List<ChatMessage> chatMessages;



    @PrePersist
    public void prePersist(){
        this.visitCount = this.visitCount == null ? 0: this.visitCount;
    }

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

    /**
     * 비밀번호를 암호화
     * @param passwordEncoder 암호화 할 인코더 클래스
     * @return 변경된 유저 Entity
     */
    public Member hashPassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
        return this;
    }

    /**
     * 비밀번호 확인
     * @param plainPassword 암호화 이전의 비밀번호
     * @param passwordEncoder 암호화에 사용된 클래스
     * @return true | false
     */
    public boolean checkPassword(String plainPassword , PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.password);
    }

    public void updateMember(String email, String memberName,String profileImg){
        this.email = email;
        this.memberName = memberName;
        this.profileImg = profileImg;

    }

    public void deleteMember(){
        this.delete = 1;
    }

    public void updatePassword(String password){
        this.password= password;
    }

    public void Authority (){
        this.authority=1;
    }


}