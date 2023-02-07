package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.admin.dto.PostResponseDTO;
import com.example.fieldpasserbe.post.dto.PostResponseDto;
import com.example.fieldpasserbe.post.service.PostSearchService;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import com.example.fieldpasserbe.post.dto.PostListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostSearchServiceImpl implements PostSearchService {

    private final PostRepositoryJPA postRepository;
    private final int contentsSize = 10;

    @Override
    public Long countPostById(int id) {
        return postRepository.countPostById(id);
    }

    @Transactional
    @Override
    public void updateViewCount(int postId) {
        postRepository.updateViewCount(postId);
    }

    @Override
    public PostResponseDto postDetailByPostId(int postId) {
        return postRepository.findByPostId(postId).map(post -> new PostResponseDto(post)).get();
    }

    @Override
    public Slice<PostListResponseDto> postList(int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findDefaultAll(pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public Slice<PostListResponseDto> postListByCategory(String category, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));
        return postRepository.findByCategory_CategoryName(category, pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public Slice<PostListResponseDto> postListByDistrict(String district, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findByDistrict_DistrictName(district, pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public Slice<PostListResponseDto> postListByCategoryAndDistrict(String category, String district, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findByCategory_CategoryNameAndDistrict_DistrictName(category, district, pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public Slice<PostListResponseDto> postListByCategoryAndDistrictAndStadium(String category, String district, String stadiumName, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "registerDate"));

        return postRepository.findByCategory_CategoryNameAndDistrict_DistrictNameAndStadium_StadiumName(category, district, stadiumName, pageRequest)
                .map(post -> new PostListResponseDto(post));
    }

    @Override
    public List<PostListResponseDto> findImminent(String category) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return postRepository.findTop20ByCategory_CategoryNameAndStartTimeAfterOrderByStartTimeAsc(category, nowDateTime)
                .stream()
                .map(post -> new PostListResponseDto(post))
                .collect(Collectors.toList());
    }

    /**
     * 회원 아이디로 게시글 조회
     * @param page
     * @param memberId
     * @return
     * @throws NullPointerException
     */
    @Override
    public Page<PostResponseDTO> findPostsById(int page, int memberId) throws NullPointerException{
        PageRequest pageRequest = PageRequest.of(page - 1, contentsSize, Sort.by(Sort.Direction.DESC, "registerDate"));
        Page<PostResponseDTO> posts = postRepository.findPostsByMemberId(memberId, pageRequest);
        if (posts.getContent().isEmpty()) {
            throw new NullPointerException("조회할 수 있는 데이터가 없습니다");
        } else {
            return posts;
        }
    }

    @Override
    public List<PostListResponseDto> findByStadiumName(String stadiumName) {
        return postRepository.findByStadium_StadiumNameOrderByRegisterDateDesc(stadiumName)
                .stream()
                .map(post -> new PostListResponseDto(post))
                .collect(Collectors.toList());
    }

    /**
     * 전체 게시글 조회(관리자)
     * 블라인드된 게시글도 보임
     * @param startDate
     * @param endDate
     * @param page
     * @return
     * @throws NullPointerException
     */
    @Override
    public Page<PostResponseDTO> lookupAllPosts(String startDate, String endDate, int page) throws Exception{
        PageRequest pageRequest = PageRequest.of(page - 1, contentsSize, Sort.by(Sort.Direction.DESC, "registerDate"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            if (end.getTime() - start.getTime() < 0) {
                throw new IllegalStateException("날짜를 잘못 입력했습니다.");
            }
            Page<PostResponseDTO> posts = postRepository.findTotalPosts(startDate, endDate, pageRequest);
            if (posts.getContent().isEmpty()) {
                throw new NullPointerException("조회할 수 있는 데이터가 없습니다");
            } else {
                return posts;
            }
        }  catch (IllegalStateException e) {
            throw new IllegalStateException("날짜를 잘못 입력했습니다.");
        }
    }
}
