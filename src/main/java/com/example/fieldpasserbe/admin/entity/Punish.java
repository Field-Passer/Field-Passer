package com.example.fieldpasserbe.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "REPORT_ID")
    private int reportId;

    @Column(name = "ADMIN_ID")
    public int adminId;

    @Column(name = "TARGET_ID")
    public int targetId;

    @Column(name = "JUDGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime judgeDate;

    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime releaseDate;

}
