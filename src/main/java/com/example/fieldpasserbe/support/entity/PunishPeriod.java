package com.example.fieldpasserbe.support.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class PunishPeriod {

    @Column(name = "JUDGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date judgeDate;

    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;
}

