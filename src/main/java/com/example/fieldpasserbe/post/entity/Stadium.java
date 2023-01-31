package com.example.fieldpasserbe.post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "STADIUM_LIST")
public class Stadium {

    @Id
    @Column(name = "STADIUM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stadiumId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICT_ID")
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
    private String defaultImageUrl;

    @OneToMany(mappedBy = "stadiumList")
    private List<Post> postList = new ArrayList<>();
}
