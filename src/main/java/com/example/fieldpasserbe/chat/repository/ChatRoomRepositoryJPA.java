package com.example.fieldpasserbe.chat.repository;

import com.example.fieldpasserbe.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepositoryJPA extends JpaRepository<ChatRoom, Integer> {
    List<ChatRoom> findBySeller_MemberIdOrBuyer_MemberId(int sellerId, int buyerId);
    @EntityGraph(attributePaths = {"seller","buyer","post"})
    Optional<ChatRoom> findByPost_PostIdAndBuyer_MemberId(int postId, int memberId);
}
