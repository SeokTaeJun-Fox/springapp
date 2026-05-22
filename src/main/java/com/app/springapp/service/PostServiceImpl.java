package com.app.springapp.service;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostAfterResponseDTO;
import com.app.springapp.domain.dto.response.PostBeforeResponseDTO;
import com.app.springapp.domain.dto.response.PostReadResponseDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.exception.PostException;
import com.app.springapp.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.http.POST;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;
    private final ReplyService replyService;
    private final PostPictureService postPictureService;

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    @Override
    public PostResponseDTO FindPost(PostReadRequestDTO postReadRequestDTO) {
        return postDAO.findById(postReadRequestDTO).orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
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

        postReadResponseDTO.setPost(FindPost(postReadRequestDTO));  //게시글 정보 저장
        postReadResponseDTO.setReplies(replyService.getPostReplies(postReadRequestDTO));    //게시글에 달린 댓글 정보(대댓글포함) 저장
        postReadResponseDTO.setPostPictures(postPictureService.findAll(postReadRequestDTO.getPostId()));    //게시글 첨부 이미지 목록 저장
        postReadResponseDTO.setBeforePost(findBeforePost(postReadRequestDTO.getPostId()));  //이전글 정보 저장
        postReadResponseDTO.setAfterPost(findAfterPost(postReadRequestDTO.getPostId()));    //다음글 정보 저장

        Long memberId = postReadResponseDTO.getPost().getMemberId();

        postReadResponseDTO.setMemberPostCount(countPost(memberId));
        postReadResponseDTO.setMemberReplyCount(replyService.countReply(memberId));

        return postReadResponseDTO;
    }


}
