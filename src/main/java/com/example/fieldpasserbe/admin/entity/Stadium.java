package com.example.fieldpasserbe.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "STADIUM_LIST")
public class Stadium {

    @Id
    @Column(name = "STADIUM_ID")
    private int stadiumId;

    @Column(name = "CATEGORY_ID")
    private int categoryId;

    @Column(name = "DISTRICT_ID")
    private int districtId;

    @Column(name = "STADIUM_NAME")
    private String stadiumName;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "LONGITUDE")
    private String longitude;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "IMAGE_URL")
    private String imageURL;
}
