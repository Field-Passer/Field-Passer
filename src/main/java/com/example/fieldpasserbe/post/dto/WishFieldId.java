package com.example.fieldpasserbe.post.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class WishFieldId implements Serializable {
    private Integer member;
    private Integer stadiumList;
}
