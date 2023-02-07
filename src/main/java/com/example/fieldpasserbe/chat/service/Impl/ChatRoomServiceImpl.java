package com.example.fieldpasserbe.chat.service.Impl;

import com.example.fieldpasserbe.chat.dto.ChatRoomRequestDto;
import com.example.fieldpasserbe.chat.dto.ChatRoomResponseDto;
import com.example.fieldpasserbe.chat.repository.ChatRoomRepositoryJPA;
import com.example.fieldpasserbe.chat.service.ChatRoomService;
import com.example.fieldpasserbe.post.entity.Category;
import com.example.fieldpasserbe.post.entity.District;
import com.example.fieldpasserbe.post.entity.Post;
import com.example.fieldpasserbe.post.entity.Stadium;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import com.example.fieldpasserbe.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepositoryJPA chatRoomRepositoryJPA;
    private final PostRepositoryJPA postRepositoryJPA;
    @Override
    public String createChatRoom(ChatRoomRequestDto chatRoomRequestDto) {
        try {
            int postId = chatRoomRequestDto.getPostId();
            Post findpost = postRepositoryJPA.findByPostId(postId).get(); //포스트 아이디를 통해 게시글을 찾아온 후
            Integer sellerId = findpost.getMember().getMemberId(); //해당 게시글에서 멤버 아이디를 가져옴

            chatRoomRequestDto.setSellerId(sellerId);

            chatRoomRepositoryJPA.save(chatRoomRequestDto.toEntity());
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }

    @Override
    public List<ChatRoomResponseDto> findMyChatRooms(int memberId) {
        return chatRoomRepositoryJPA.findBySeller_MemberIdOrBuyer_MemberId(memberId, memberId)
                .stream()
                .map(chatRoom -> new ChatRoomResponseDto(chatRoom))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChatRoomResponseDto> findByPostIdAndMemberId(int postId, int memberId) {
        return chatRoomRepositoryJPA.findByPost_PostIdAndBuyer_MemberId(postId, memberId)
                .map(chatRoom -> new ChatRoomResponseDto(chatRoom));
    }
}
