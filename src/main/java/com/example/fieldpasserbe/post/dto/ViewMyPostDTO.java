package com.example.fieldpasserbe.post.dto;


import com.example.fieldpasserbe.post.entity.Post;
import com.example.fieldpasserbe.post.entity.TransactionStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ViewMyPostDTO {

    private int postId;
    private int memberId;
    private String categoryName;
    private String districtName;
    private String stadiumName;

    private String title;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime registerDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private TransactionStatus transactionStatus;

    private int price;

    public ViewMyPostDTO(Post post) {

    this.postId = post.getPostId();
    this.memberId = post.getMember().getMemberId();
    this.categoryName = post.getCategory().getCategoryName();
    this.districtName = post.getDistrict().getDistrictName();
    this.stadiumName = post.getDistrict().getDistrictName();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.registerDate = post.getRegisterDate();
    this.startTime = post.getStartTime();
    this.endTime = post.getEndTime();
    this.transactionStatus = post.getTransactionStatus();
    this.price = post.getPrice();

    }
}
