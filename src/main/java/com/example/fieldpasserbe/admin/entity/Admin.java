package com.example.fieldpasserbe.admin.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ADMINLIST")
public class Admin {

    @Id
    @Column(name = "ADMIN_ID")
    private int admin_id;

    @Column(name = "PROMOTE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date promoteDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    private Member member;
}