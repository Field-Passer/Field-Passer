package com.example.fieldpasserbe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Post")
public class Post {
    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryList categoryList;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "DELETE_DATE")
    private LocalDateTime deleteDate;

    @Column(name = "START_TIME", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "END_TIME", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @Column(name = "TRANSACTION_STATUS")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "VIEW_COUNT", nullable = false)
    private Integer viewCount;

    @Column(name = "WISH_COUNT", nullable = false)
    private Integer wishCount;

    @Column(name = "BLIND" , columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private int blind;

    @Column(name = "DELETE_CHECK", columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private int deleteCheck;


}
