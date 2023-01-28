package com.example.fieldpasserbe.admin.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date promoteDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    private Member member;
}