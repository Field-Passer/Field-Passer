package com.example.fieldpasserbe.admin.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PeriodRequestDTO {

    private String startDate;
    private String endDate;
}
