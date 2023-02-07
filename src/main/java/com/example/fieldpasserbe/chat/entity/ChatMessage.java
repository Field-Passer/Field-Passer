package com.example.fieldpasserbe.chat.entity;

import com.example.fieldpasserbe.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "CHAT_MESSAGE")
public class ChatMessage {
    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_ID")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private Member member;

    @Column(name = "CONTENT")
    private String content;

    @CreationTimestamp
    @Column(name = "CREATED")
    private LocalDateTime created;

    @Column(name = "STATUS", columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private int status;

}
