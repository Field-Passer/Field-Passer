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
@Table(name = "DISTRICT_LIST")
public class DistrictList {

    @Id
    @Column(name = "DISTRICT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int districtId;

    @Column(name = "DISTRICT")
    private String district;

    @OneToMany(mappedBy = "districtList")
    private List<Post> postList = new ArrayList<>();
}
