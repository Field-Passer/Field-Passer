package com.example.fieldpasserbe.admin.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "BOARD")
public class Board {

    enum TransactionStatus {
        거래_중, 예약_중, 거래_완료
    }

    @Id
    @Column(name = "POST_ID")
    private int postId;

    @Column(name = "ID")
    private int id;

    @Column(name = "CATEGORY_ID")
    private int categoryId;

    @Column(name = "DISTRICT_ID")
    private int districtId;

    @Column(name = "STADIUM_ID")
    private int stadiumId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REGISTER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "DELETE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;

    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @Column(name = "TRANSACTION_STATUS")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "VIEW_COUNT")
    private int viewCount;

    @Column(name = "WISH_COUNT")
    private int wishCount;

    @Column(name = "BLIND")
    private byte blind;

    @Column(name = "DELETE_CHECK")
    private byte deleteCheck;
}