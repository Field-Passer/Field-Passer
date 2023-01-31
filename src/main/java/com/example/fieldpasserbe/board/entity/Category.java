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
@Table(name = "CATEGORY_LIST")
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    private int categoryId;

    @Column(name = "CATEGORY")
    private String category;
}