package com.app.springapp.service;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.PostUpdateRequestDTO;
import com.app.springapp.domain.dto.response.*;
import com.app.springapp.domain.vo.PostVO;
import com.app.springapp.exception.PostException;
import com.app.springapp.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;
    private final PostLikeService postLikeService;
    private final ReplyService replyService;
    private final PostPictureService postPictureService;

    //검색 결과 만족하는 게시글 리스트로 반환
    @Override
    public List<PostListResponseDTO> getPostList(Map<String, Object> order) {
        return postDAO.selectPostList(order);
    }

    //검색 결과 만족하는 내 게시글 리스트로 반환
    @Override
    public List<PostListResponseDTO> getMyPostList(Map<String, Object> order) {
        return postDAO.findMyPostAll(order);
    }

    //검색 결과 게시글 목록 갯수 (페이지 조건제외)
    @Override
    public Integer getPostCount(Map<String, Object> order) {
        return postDAO.getPostCount(order);
    }

    //검색 결과 내 게시글 목록 갯수 (페이지 조건제외)
    @Override
    public Integer getMyPostCount(Map<String, Object> order) {
        return postDAO.getMyPostCount(order);
    }

    //검색 결과 정보 DTO반환(게시글 총 갯수(페이지 조건제외) + 게시글 목록)
    @Override
    public CommunityPostListResponseDTO getSearchResult(Map<String, Object> order) {
        CommunityPostListResponseDTO communityPostListResponseDTO = new CommunityPostListResponseDTO();
        communityPostListResponseDTO.setTotal(getPostCount(order));
        communityPostListResponseDTO.setPosts(getPostList(order));
        return communityPostListResponseDTO;
    }

    //검색 결과 내 게시글 정보 DTO반환(게시글 총 갯수(페이지 조건제외) + 게시글 목록)
    @Override
    public CommunityPostListResponseDTO getMyPostSearchResult(Map<String, Object> order) {
        CommunityPostListResponseDTO communityPostListResponseDTO = new CommunityPostListResponseDTO();
        communityPostListResponseDTO.setTotal(getMyPostCount(order));
        communityPostListResponseDTO.setPosts(getMyPostList(order));
        return communityPostListResponseDTO;
    }

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    @Override
    public PostResponseDTO findPost(PostReadRequestDTO postReadRequestDTO) {
        return postDAO.findById(postReadRequestDTO).orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
    }

    //id로 게시글 검색
    @Override
    public PostVO findPost(Long id) {
        return postDAO.find(id).orElseThrow(() -> new PostException("게시글을 찾지 못했습니다.", HttpStatus.NOT_FOUND));
    }

    // POST ID로 이전글 찾기
    @Override
    public PostBeforeResponseDTO findBeforePost(Long postId) {
        return postDAO.findBeforePost(postId);
    }

    //POST ID로 다음글 찾기
    @Override
    public PostAfterResponseDTO findAfterPost(Long postId) {
        return postDAO.findAfterPost(postId);
    }

    //작성자가 작성한 게시글 수
    @Override
    public Integer countPost(Long memberId) {
        return postDAO.countPost(memberId);
    }

    //게시판 상세페이지에 모든 정보 불러오기
    @Override
    public PostReadResponseDTO getPostDetailInfo(PostReadRequestDTO postReadRequestDTO) {
        PostReadResponseDTO postReadResponseDTO = new PostReadResponseDTO();

        postReadResponseDTO.setPost(findPost(postReadRequestDTO));  //게시글 정보 저장
        postReadResponseDTO.setReplies(replyService.getPostReplies(postReadRequestDTO));    //게시글에 달린 댓글 정보(대댓글포함) 저장
//        postReadResponseDTO.setPostPictures(postPictureService.findAll(postReadRequestDTO.getPostId()));    //게시글 첨부 이미지 목록 저장
        postReadResponseDTO.setBeforePost(findBeforePost(postReadRequestDTO.getPostId()));  //이전글 정보 저장
        postReadResponseDTO.setAfterPost(findAfterPost(postReadRequestDTO.getPostId()));    //다음글 정보 저장

        Long memberId = postReadResponseDTO.getPost().getMemberId();

        postReadResponseDTO.setMemberPostCount(countPost(memberId));
        postReadResponseDTO.setMemberReplyCount(replyService.countReply(memberId));

        //게시글 조회수 증가
        increaseReadCount(postReadRequestDTO.getPostId());

        return postReadResponseDTO;
    }

    //게시글 작성 (PostCreateResponseDTO는 새로 작성된 게시글 번호정보가 들어있다.)
    @Override
    public PostCreateResponseDTO writePost(PostCreateDTO postCreateDTO) {

        postDAO.save(postCreateDTO);

        PostCreateResponseDTO postCreateResponseDTO = new PostCreateResponseDTO();
        postCreateResponseDTO.setPostId(postCreateDTO.getId());

        return postCreateResponseDTO;
    }

    //게시글 수정
    @Override
    public void updatePost(PostUpdateRequestDTO postUpdateRequestDTO) {
        postDAO.update(postUpdateRequestDTO);
    }

    //게시글 조회수 증가
    @Override
    public void increaseReadCount(Long postId) {
        postDAO.increaseReadCount(postId);
    }

    //게시글 삭제
    @Override
    public void deletePost(Long postId) {
        //게시글 모든 좋아요 삭제
        postLikeService.cancelPostLikeAll(postId);

        //게시글에 달린 모든 댓글 삭제
        replyService.deleteReplies(postId);

        //게시글 삭제
        postDAO.delete(postId);
    }
}
