package com.app.springapp.service;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.PostUpdateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
@Slf4j
public class PostTests {

    @Autowired
    private PostService postService;

    @Test
    public void findPostByMemberIdandIdTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setMemberId(2L);
        postReadRequestDTO.setPostId(20L);
        log.info("Post Read RequestDTO : {}", postService.findPost(postReadRequestDTO));
    }

    @Test
    public void findBeforePostTest() {
        log.info("{}", postService.findBeforePost(1L));
    }

    @Test
    public void findAfterPostTest() {
        log.info("{}", postService.findAfterPost(20L));
    }

    @Test
    public void countPostTest() {
        log.info("{}", postService.countPost(3L));
    }

    //2단계 테스트
    @Test
    public void findPostDetailTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setPostId(20L);
        postReadRequestDTO.setMemberId(2L);
        log.info("Post Read RequestDTO : {}", postService.getPostDetailInfo(postReadRequestDTO));
    }

    @Test
    public void writePostTest() {
        PostCreateDTO postCreateDTO = new PostCreateDTO();
        postCreateDTO.setPostTitle("테스트 제목");
        postCreateDTO.setPostContent("테스트 내용");
        postCreateDTO.setMemberId(2L);
        postCreateDTO.setCategoryId(2L);

        log.info("추가된 게시글 응답 정보 : {}",postService.writePost(postCreateDTO));
    }

    @Test
    public void updatePostTest() {
        PostUpdateRequestDTO postUpdateRequestDTO = new PostUpdateRequestDTO();
        postUpdateRequestDTO.setId(28L);
        postUpdateRequestDTO.setPostTitle("테스트로 수정된 제목");
        postUpdateRequestDTO.setPostContent("테스트로 수정된 내용");
        postUpdateRequestDTO.setCategoryId(4L);
        postService.updatePost(postUpdateRequestDTO);
    }

    @Test
    public void findPostTest() {
        log.info("{}", postService.findPost(1L));
    }

    @Test
    public void deletePostTest() {
        postService.deletePost(28L);
    }

    @Test
    public void findPostListTest() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("order", 1);
        hashMap.put("page", 2);
        hashMap.put("order2", 0);
        hashMap.put("category", 0);
        hashMap.put("content", "img");
        log.info("{}",postService.getPostList(hashMap));
    }

    @Test
    public void searchPostResultTest() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("order", 1);
        hashMap.put("page", 1);
        hashMap.put("order2", 0);
        hashMap.put("category", 0);
        hashMap.put("content", "img");
        log.info("{}",postService.getSearchResult(hashMap));
    }

    @Test
    public void findPostCountTest() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("order", 1);
        hashMap.put("page", 2);
        hashMap.put("order2", 0);
        hashMap.put("category", 0);
        hashMap.put("content", "img");
        log.info("{}",postService.getPostCount(hashMap));
    }
}
