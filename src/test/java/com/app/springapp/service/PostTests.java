package com.app.springapp.service;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.PostUpdateRequestDTO;
import com.app.springapp.domain.vo.PostVO;
import com.app.springapp.repository.PostDAO;
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
    @Autowired
    private PostDAO postDAO;

    @Test
    public void findPostByMemberIdandIdTest() {
        PostReadRequestDTO postReadRequestDTO = new PostReadRequestDTO();
        postReadRequestDTO.setMemberId(2L);
        postReadRequestDTO.setPostId(20L);
        log.info("Post Read RequestDTO : {}", postService.findPost(postReadRequestDTO));
    }

    @Test
    public void findBeforePostTest() {
        log.info("{}", postService.findBeforePost(2L));
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
    public void searchMyPostResultTest() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("order", 0);
        hashMap.put("page", 1);
        hashMap.put("content", "");
        hashMap.put("memberId", 1L);
        log.info("{}",postService.getMyPostSearchResult(hashMap));
    }

    @Test
    public void searchMyPostALLTest() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("order", 0);
        hashMap.put("page", 1);
        hashMap.put("content", "");
        hashMap.put("memberId", 1L);
        log.info("{}",postService.getMyPostList(hashMap));
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

    //인기글 목록 불러오기 테스트
    @Test
    public void findPopularPostsTest() {
        log.info("{}", postService.findPopularPosts(1L));
    }

    //지난달 인기글 불러오기 테스트
    @Test
    public void findPopularPostsLastMonthTest() {
        log.info("{}", postService.findPopularPostAtLastMonth(1L));
    }

    //메인 커뮤니티 초기 정보 불러오기 테스트
    @Test
    public void getCommunityInitialTest() {
        log.info("{}", postService.getCommunityInfo(1L));
    }

    @Test
    public void aiTest() {
        postService.getPostAiRecommand(1L);
    }

    @Test
    public void test2() {
        PostVO postVO = new PostVO();
        postVO.setMemberId(1L);
        postVO.setId(1L);
        log.info("{}", postDAO.findByMemberIdAndPostId(postVO));
    }

    @Test
    public void findPostIdAndPostContentByIdTest() {
        log.info("{}", postService.findIdAndPostContentById(1L));
    }

    @Test
    public void findIdAndPostContentExceptIdTest() {
        log.info("{}", postDAO.findIdAndPostContentExceptId(1L));
    }

    @Test
    public void aiTest2(){
        log.info("{}", postService.getPostAiRecommand(1L, 1L));
    }
}
