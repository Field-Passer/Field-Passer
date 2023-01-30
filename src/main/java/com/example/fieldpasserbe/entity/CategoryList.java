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
@Table(name = "CATEGORY_LIST")
public class CategoryList {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "CATEGORY")
    private String category;

    @OneToMany(mappedBy = "categoryList")
    private List<Post> postList = new ArrayList<>();

}
