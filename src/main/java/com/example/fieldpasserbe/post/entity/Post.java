package com.example.fieldpasserbe.post.entity;

import com.example.fieldpasserbe.chat.entity.ChatRoom;
import com.example.fieldpasserbe.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "POST")
@Where(clause = "DELETE_CHECK = 0 AND BLIND = 0")
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
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICT_ID")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STADIUM_ID")
    private Stadium stadium;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

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

    @OneToMany(mappedBy = "post")
    private List<ChatRoom> chatRooms;

    @PrePersist
    public void prePersist() {
        this.viewCount = this.viewCount == null ? 0 : this.viewCount;
        this.wishCount = this.wishCount == null ? 0 : this.wishCount;
    }

    public void updatePost(Category category, District district, Stadium stadium,
                           String title, String content, LocalDateTime startTime, LocalDateTime endTime,
                           String imageUrl, TransactionStatus transactionStatus, int price) {
        this.category = category;
        this.district = district;
        this.stadium = stadium;
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.imageUrl = imageUrl;
        this.transactionStatus = transactionStatus;
        this.price = price;
    }

    public void deletePost() {
        this.deleteDate = LocalDateTime.now();
        this.deleteCheck = 1;
    }

    public void blindPost() {
        this.deleteDate = LocalDateTime.now();
        this.blind = 1;
    }

    public void BlindPost() {
        this.blind = 1;
    }
    public void unBlindPost() {
        this.blind = 0;
    }
}