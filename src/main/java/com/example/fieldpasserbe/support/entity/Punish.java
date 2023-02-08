package com.example.fieldpasserbe.support.entity;

import com.example.fieldpasserbe.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "PUNISH_LIST")
public class Punish {

    @Id
    @GeneratedValue
    @Column(name = "PUNISH_ID")
    private int punishId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    private Report report;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID", referencedColumnName = "ID")
    private Member admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TARGET_ID", referencedColumnName = "ID")
    private Member target;

    @Embedded
    private PunishPeriod punishPeriod;

}