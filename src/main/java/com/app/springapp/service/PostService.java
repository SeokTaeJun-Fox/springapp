package com.app.springapp.service;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.PostUpdateRequestDTO;
import com.app.springapp.domain.dto.response.*;
import com.app.springapp.domain.vo.PostVO;

import java.util.List;
import java.util.Map;


public interface PostService {

    //게시글 검색 결과 불러오기
    public List<PostListResponseDTO> getPostList(Map<String, Object> order);

    //내 게시글 검색 리스트 불러오기
    public List<PostListResponseDTO> getMyPostList(Map<String, Object> order);

    //검색 결과 게시글 리스트 갯수 반환
    public Integer getPostCount(Map<String, Object> order);

    //검색 결과 내 게시글 리스트 갯수 반환
    public Integer getMyPostCount(Map<String, Object> order);

    //검색 결과 정보 DTO반환(게시글 총 갯수(페이지 조건제외) + 게시글 목록)
    public CommunityPostListResponseDTO getSearchResult(Map<String, Object> order);

    //검색 결과 내 게시글 정보 DTO반환(게시글 총 갯수(페이지 조건제외) + 게시글 목록)
    public CommunityPostListResponseDTO getMyPostSearchResult(Map<String, Object> order);

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    public PostResponseDTO findPost(PostReadRequestDTO postReadRequestDTO);

    //게시글 id로 게시글 정보 불러오기
    public PostVO findPost(Long id);

    // POST ID로 이전글 찾기
    public PostBeforeResponseDTO findBeforePost(Long postId);

    //POST ID로 다음글 찾기
    public PostAfterResponseDTO findAfterPost(Long postId);

    //작성자가 작성한 게시글 수
    public Integer countPost(Long memberId);

    //게시판 상세페이지에 모든 정보 불러오기
    public PostReadResponseDTO getPostDetailInfo(PostReadRequestDTO postReadRequestDTO);

    //게시글 작성 (PostCreateResponseDTO는 새로 작성된 게시글 번호정보가 들어있다.)
    public PostCreateResponseDTO writePost(PostCreateDTO postCreateDTO);

    //게시글 수정
    public void updatePost(PostUpdateRequestDTO postUpdateRequestDTO);

    //게시글 조회수 증가
    public void increaseReadCount(Long postId);

    //게시글 삭제
    public void deletePost(Long postId);
}
