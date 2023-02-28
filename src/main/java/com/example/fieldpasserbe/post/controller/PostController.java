package com.example.fieldpasserbe.post.controller;

import com.example.fieldpasserbe.global.response.ResponseDTO;
import com.example.fieldpasserbe.post.dto.*;
import com.example.fieldpasserbe.post.service.PostSearchService;
import com.example.fieldpasserbe.post.service.PostService;
import com.example.fieldpasserbe.post.service.ViewMyPostService;
import com.example.fieldpasserbe.post.service.WishPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostSearchService postSearchService;
    private final PostService postService;
    private final WishPostService wishPostService;

    private final ViewMyPostService viewMyPostService;

    @GetMapping("/api/post/{postId}")
    public PostResponseDto postDetail(@PathVariable int postId) {
        postSearchService.updateViewCount(postId);
        return postSearchService.postDetailByPostId(postId);
    }

    @GetMapping("/api/post")
    public Slice<PostListResponseDto> postList(@RequestParam(name = "page") int page) {
        return postSearchService.postList(page);
    }

    // 구장 종류별 조회
    @GetMapping("/api/post/category")
    public Slice<PostListResponseDto> postListByCategory(@RequestParam(name = "category") String category,
                                                         @RequestParam(name = "page") int page) {
        return postSearchService.postListByCategory(category, page);
    }

    @GetMapping("/api/post/district")
    public Slice<PostListResponseDto> postListByDistrict(@RequestParam(name = "district") String district,
                                                         @RequestParam(name = "page") int page) {
        return postSearchService.postListByDistrict(district, page);
    }

    @GetMapping("/api/post/category/district")
    public Slice<PostListResponseDto> postListByCategoryAndDistrict(@RequestParam(name = "category") String category,
                                                                    @RequestParam(name = "district") String district,
                                                                    @RequestParam(name = "page") int page) {
        return postSearchService.postListByCategoryAndDistrict(category, district, page);
    }

    @GetMapping("/api/post/stadium")
    public Slice<PostListResponseDto> postListByCategoryAndDistrictAndStadium(@RequestParam(name = "category") String category,
                                                                              @RequestParam(name = "district") String district,
                                                                              @RequestParam(name = "stadiumName") String stadiumName,
                                                                              @RequestParam(name = "page") int page) {
        return postSearchService.postListByCategoryAndDistrictAndStadium(category, district, stadiumName, page);
    }

    @PostMapping("/api/post/write")
    public String insertPost(@RequestParam("file") MultipartFile file, PostRequestDto postRequestDto) {
        return postService.insertPost(file, postRequestDto);
    }

    @PutMapping("/api/post/edit/{postId}")
    public String editPost(@PathVariable int postId, @RequestParam("file") MultipartFile file, PostRequestDto postRequestDto) {
        return postService.editPost(postId, file, postRequestDto);
    }

    @PutMapping("/api/post/delete/{postId}")
    public String deletePost(@PathVariable int postId) {
        return postService.deletePost(postId);
    }

    @PostMapping("/api/like/post")
    public String likePost(WishPostRequestDto wishPostRequestDto) {
        return wishPostService.likePost(wishPostRequestDto);
    }

    @GetMapping("/api/imminent")
    public List<PostListResponseDto> findImminent(@RequestParam(name = "category") String category) {
        return postSearchService.findImminent(category);
    }

    @GetMapping("/api/myPage/like/post") //추후 세션으로 변경
    public List<WishPostResponseDto> myLikePostList(@RequestParam(name = "memberId") int memberId) {
        return wishPostService.myLikePostList(memberId);
    }

    @GetMapping("/api/post/stadium/{stadiumName}")
    public List<PostListResponseDto> myLikeStadiums(@PathVariable String stadiumName) {
        return postSearchService.findByStadiumName(stadiumName);
    }

    @GetMapping("/api/search/post/:memberId")
    public ResponseDTO<?> selectMyPost(HttpSession session){
        Integer Id = (int)session.getAttribute("id");

        return viewMyPostService.selectMyPost(Id);
    }
}
