package com.example.fieldpasserbe.post.entity;

import com.example.fieldpasserbe.seok.entity.Post;
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
@Table(name = "CATEGORY_LIST")
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "CATEGORY")
    private String category;

    @OneToMany(mappedBy = "categoryList")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "categoryList")
    private List<Stadium> stadiumList = new ArrayList<>();
}