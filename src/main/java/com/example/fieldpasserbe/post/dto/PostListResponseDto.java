package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.post.entity.Post;
import com.example.fieldpasserbe.post.entity.TransactionStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostListResponseDto {
    private int postId;
    private int memberId;
    private String memberName;
    private String categoryName;
    private String districtName;
    private String stadiumName;
    private String title;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String defaultImageUrl;
    private TransactionStatus transactionStatus;
    private int price;
    private int viewCount;
    private int wishCount;
    private int blind;
    private int deleteCheck;

    public PostListResponseDto(Post post) {
        this.postId = post.getPostId();
        this.memberId = post.getMember().getId();
        this.memberName = post.getMember().getMemberName();
        this.categoryName = post.getCategory().getCategoryName();
        this.districtName = post.getDistrict().getDistrictName();
        this.stadiumName = post.getStadium().getStadiumName();
        this.title = post.getTitle();
        this.registerDate = post.getRegisterDate();
        this.updateDate = post.getUpdateDate();
        this.deleteDate = post.getDeleteDate();
        this.startTime = post.getStartTime();
        this.endTime = post.getEndTime();
        this.defaultImageUrl = post.getStadium().getDefaultImageUrl();
        this.transactionStatus = post.getTransactionStatus();
        this.price = post.getPrice();
        this.viewCount = post.getViewCount();
        this.wishCount = post.getWishCount();
        this.blind = post.getBlind();
        this.deleteCheck = post.getDeleteCheck();
    }

}
