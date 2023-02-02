package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.post.dto.PostRequestDto;
import com.example.fieldpasserbe.post.entity.Category;
import com.example.fieldpasserbe.post.entity.District;
import com.example.fieldpasserbe.post.entity.Post;
import com.example.fieldpasserbe.post.entity.Stadium;
import com.example.fieldpasserbe.post.repository.CategoryRepositoryJPA;
import com.example.fieldpasserbe.post.repository.DistrictRepositoryJPA;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import com.example.fieldpasserbe.post.repository.StadiumRepositoryJPA;
import com.example.fieldpasserbe.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepositoryJPA postRepository;
    private final CategoryRepositoryJPA categoryRepository;
    private final DistrictRepositoryJPA districtRepository;
    private final StadiumRepositoryJPA stadiumRepository;

    @Value("${site-file.upload-dir}")
    private String uploadDir;
    @Transactional
    @Override
    public String insertPost(MultipartFile file, PostRequestDto postRequestDto) {
        try {
            String image = uploadPic(file);
            postRequestDto.setImageUrl(image);

            Category category = categoryRepository.findByCategoryName(postRequestDto.getCategoryName()).get();
            District district = districtRepository.findByDistrictName(postRequestDto.getDistrictName()).get();
            Stadium stadium = stadiumRepository.findByStadiumName(postRequestDto.getStadiumName()).get();

            postRepository.save(postRequestDto.toEntity(category, district, stadium));
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }
    @Transactional
    @Override
    public String editPost(int postId, MultipartFile file, PostRequestDto postRequestDto) {
        try {
            String image = uploadPic(file); //수정된 파일 저장
            postRequestDto.setImageUrl(image); //이미지명 변경

            Post findPost = postRepository.findByPostId(postId).get();
            Category category = categoryRepository.findByCategoryName(postRequestDto.getCategoryName()).get();
            District districtList = districtRepository.findByDistrictName(postRequestDto.getDistrictName()).get();
            Stadium stadiumList = stadiumRepository.findByStadiumName(postRequestDto.getStadiumName()).get();

            findPost.updatePost(category, districtList, stadiumList,
                    postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getStartTime(),
                    postRequestDto.getEndTime(), postRequestDto.getImageUrl(),
                    postRequestDto.getTransactionStatus(), postRequestDto.getPrice());

        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }
    @Transactional
    @Override
    public String deletePost(int postId) {
        try {
            Post findPost = postRepository.findByPostId(postId).get();
            findPost.deletePost();
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }

    public String uploadPic(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.isDirectory(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        UUID uuid = UUID.randomUUID(); // 중복 방지를 위한 랜덤 값
        String originFileName = file.getOriginalFilename(); //파일 원래 이름
        String fullPath = uploadDir + uuid.toString() + "_" + originFileName;
        file.transferTo(new File(fullPath));

        return fullPath;
    }
}
