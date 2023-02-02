package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostRequestDto {

    private int memberId;
    private String categoryName;
    private String districtName;
    private String stadiumName;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    private String imageUrl;
    private TransactionStatus transactionStatus;
    private int price;

    public Post toEntity(Category category, District district, Stadium stadium) {
        return Post.builder()
                .member(Member.builder().memberId(memberId).build())
                .category(category)
                .district(district)
                .stadium(stadium)
                .title(title)
                .content(content)
                .startTime(startTime)
                .endTime(endTime)
                .imageUrl(imageUrl)
                .transactionStatus(transactionStatus)
                .price(price)
                .build();
    }
}
