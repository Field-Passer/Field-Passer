package com.example.fieldpasserbe.support.entity;

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
@Table(name = "Report")
public class Report {

    @Id
    @GeneratedValue
    @Column(name = "REPORT_ID")
    private int reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private Member reporterMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TARGET_ID", referencedColumnName = "ID")
    private Member targetMember;

    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private ReportCategory reportCategory;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REPORT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;

    @Column(name = "PROCESS")
    @Enumerated(EnumType.STRING)
    private ReportProcess reportProcess;
}
