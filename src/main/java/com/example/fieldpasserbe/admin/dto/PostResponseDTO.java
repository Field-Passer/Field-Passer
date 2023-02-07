package com.example.fieldpasserbe.admin.dto;

import java.time.LocalDateTime;

public interface PostResponseDTO {
    int getPostId();
    String getMemberId();
    String getMemberName();
    String getCategory();
    String getDistrict();
    String getStadiumName();
    LocalDateTime getRegisterDate();
    LocalDateTime getStartTime();
    LocalDateTime getendTime();
    String getTitle();
    int getPrice();
    String getTransactionStatus();
    boolean getBlind();
}
