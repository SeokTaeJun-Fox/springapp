package com.app.springapp.repository;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.PostUpdateRequestDTO;
import com.app.springapp.domain.dto.response.PostAfterResponseDTO;
import com.app.springapp.domain.dto.response.PostBeforeResponseDTO;
import com.app.springapp.domain.dto.response.PostListResponseDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.domain.vo.PostVO;
import com.app.springapp.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;

    //게시글 검색 결과 목록 불러오기
    public List<PostListResponseDTO> selectPostList(Map<String, Object> order) {
        return postMapper.selectAll(order);
    }

    //검색 후 자기가 쓴 게시글 목록 불러오기
    public List<PostListResponseDTO> findMyPostAll(Map<String, Object> order) {
        return postMapper.selectMyPostAll(order);
    }

    //검색 결과 게시글 목록 갯수 반환
    public Integer getPostCount(Map<String, Object> order) {
       return postMapper.CountPost(order);
    }

    //검색 결과 내게시글 목록 갯수 반환
    public Integer getMyPostCount(Map<String, Object> order) {
        return postMapper.CountMyPost(order);
    }

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    public Optional<PostResponseDTO> findById(PostReadRequestDTO postReadRequestDTO) {
        return Optional.ofNullable(postMapper.selectById(postReadRequestDTO));
    }

    //id로 게시글 정보 불러오기
    public Optional<PostVO> find(Long id) {
        return Optional.ofNullable(postMapper.select(id));
    }

    // POST ID로 이전글 찾기
    public PostBeforeResponseDTO findBeforePost(Long postId) {
        return postMapper.selectBeforePost(postId);
    }

    //POST ID로 다음글 찾기
    public PostAfterResponseDTO findAfterPost(Long postId) {
        return postMapper.selectAfterPost(postId);
    }

    //작성자가 작성한 게시글 수
    public Integer countPost(Long memberId) {
        return postMapper.selectPostCountByMemberId(memberId);
    }

    //게시글 작성
    public void save(PostCreateDTO postCreateDTO) {
        postMapper.insert(postCreateDTO);
    }

    //게시글 수정
    public void update(PostUpdateRequestDTO postUpdateRequestDTO) {
        postMapper.update(postUpdateRequestDTO);
    }

    //게시글 조회수 증가
    public void increaseReadCount(Long postId) {
        postMapper.updatePostReadCount(postId);
    }

    //게시글 삭제
    public void delete(Long postId) {
        postMapper.delete(postId);
    }
}
