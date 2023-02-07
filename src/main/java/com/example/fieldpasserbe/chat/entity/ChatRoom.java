package com.example.fieldpasserbe.chat.entity;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "CHAT_ROOM")
public class ChatRoom {

    @Id
    @Column(name = "CHAT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "ID")
    private Member seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUYER_ID", referencedColumnName = "ID")
    private Member buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @CreationTimestamp
    @Column(name = "CREATED")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "UPDATED")
    private LocalDateTime updated;

    @Column(name = "DELETE_CHECK", columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private int deleteCheck;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessages;
}
