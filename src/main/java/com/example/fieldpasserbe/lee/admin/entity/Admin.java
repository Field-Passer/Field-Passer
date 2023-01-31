package com.example.fieldpasserbe.lee.admin.entity;

import com.example.fieldpasserbe.lee.member.entity.Member;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "ADMINLIST")
public class Admin {

    @Id
    @Column(name = "ADMIN_ID")
    private int adminId;

    @Column(name = "PROMOTE_DATE")
    private LocalDateTime promoteDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    private Member member;

    public void promote() {
        member.promote();
    }
}