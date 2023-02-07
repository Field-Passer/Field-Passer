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
public class PostResponseDto {

    private int postId;
    private String memberName;
    private String categoryName;
    private String districtName;
    private String stadiumName;
    private String title;
    private String content;
    private String latitude;
    private String longitude;
    private LocalDateTime registerDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String defaultImageUrl;
    private String imageUrl;
    private TransactionStatus transactionStatus;
    private int price;
    private String phone;
    private int viewCount;
    private int wishCount;
    private int blind;
    private int deleteCheck;

    public PostResponseDto (Post post) {
        this.postId = post.getPostId();
        this.memberName = post.getMember().getMemberName();
        this.categoryName = post.getCategory().getCategoryName();
        this.districtName = post.getDistrict().getDistrictName();
        this.stadiumName = post.getStadium().getStadiumName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.latitude = post.getStadium().getLatitude();
        this.longitude = post.getStadium().getLongitude();
        this.registerDate = post.getRegisterDate();
        this.startTime = post.getStartTime();
        this.endTime = post.getEndTime();
        this.imageUrl = post.getImageUrl();
        this.defaultImageUrl = post.getStadium().getDefaultImageUrl();
        this.transactionStatus = post.getTransactionStatus();
        this.price = post.getPrice();
        this.phone = post.getStadium().getPhone();
        this.viewCount = post.getViewCount();
        this.wishCount = post.getWishCount();
        this.blind = post.getBlind();
        this.deleteCheck = post.getDeleteCheck();
    }
}
