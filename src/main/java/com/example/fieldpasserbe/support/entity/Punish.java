package com.example.fieldpasserbe.serviceCenter.entity;

import com.example.fieldpasserbe.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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

    @OneToOne
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "ADMIN_ID", referencedColumnName = "ID")
    public Member admin;

    @ManyToOne
    @JoinColumn(name = "TARGET_ID", referencedColumnName = "ID")
    public Member target;

    @Column(name = "JUDGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date judgeDate;

    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;
}