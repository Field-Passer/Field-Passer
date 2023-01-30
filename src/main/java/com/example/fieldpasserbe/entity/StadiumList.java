package com.example.fieldpasserbe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STADIUM_LIST")
public class StadiumList {

    @Id
    @Column(name = "STADIUM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stadiumId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryList categoryList;

    @Column(name = "STADIUM_NAME")
    private String stadiumName;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "LONGITUDE")
    private String longitude;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "IMAGE_URL")
    private String defaultImageUrl;

    @OneToMany(mappedBy = "stadiumList")
    private List<Post> postList = new ArrayList<>();
}
