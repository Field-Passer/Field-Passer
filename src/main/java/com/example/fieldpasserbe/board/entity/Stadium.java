package com.example.fieldpasserbe.board.entity;

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
@Table(name = "STADIUM_LIST")
public class Stadium {

    @Id
    @Column(name = "STADIUM_ID")
    private int stadiumId;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "DISTRICT_ID")
    private District district;

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
