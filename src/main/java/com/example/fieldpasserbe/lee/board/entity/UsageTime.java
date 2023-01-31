package com.example.fieldpasserbe.lee.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class UsageTime {

    @Column(name = "DELETE_DATE")
    private LocalDateTime deleteDate;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;
}
