package com.example.fieldpasserbe.admin.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "BOARD")
public class Board {

    @Id
    @Column(name = "POST_ID")
    private int postId;

    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    @Column(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID")
    @Column(name = "DISTRICT_ID")
    private District district;

    @ManyToOne
    @JoinColumn(name = "STADIUM_ID")
    @Column(name = "STADIUM_ID")
    private Stadium stadium;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REGISTER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;

    @Column(name = "DELETE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deleteDate;

    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @Column(name = "TRANSACTION_STATUS")
    private byte transactionStatus;

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