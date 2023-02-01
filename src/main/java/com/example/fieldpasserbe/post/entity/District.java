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
@Table(name = "DISTRICT_LIST")
public class District {

    @Id
    @Column(name = "DISTRICT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int districtId;

    @Column(name = "DISTRICT")
    private String districtName;

    @OneToMany(mappedBy = "district")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "district")
    private List<Stadium> stadiumList = new ArrayList<>();
}