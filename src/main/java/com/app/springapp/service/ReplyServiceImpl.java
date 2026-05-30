package com.app.springapp.service;

import com.app.springapp.domain.dto.ReplyDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.ReplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.ReplyUpdateRequestDTO;
import com.app.springapp.domain.dto.response.PostReadReplyResponseDTO;
import com.app.springapp.repository.ReplyDAO;
import com.app.springapp.repository.ReplyLikeDAO;
import com.app.springapp.repository.RereplyDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDAO replyDAO;
    private final ReplyLikeDAO replyLikeDAO;
    private final RereplyDAO rereplyDAO;

    // 게시글id와 멤버id로 댓글 정보(댓글에 달린 대댓글포함) 목록 불러오기
    @Override
    public List<PostReadReplyResponseDTO> getPostReplies(PostReadRequestDTO postReadRequestDTO) {
        List<ReplyDTO> replyDTOList = replyDAO.findAll(postReadRequestDTO);

        return replyDTOList
                .stream()
                .map((replyDTO) -> {
                    PostReadReplyResponseDTO postReadReplyResponseDTO = PostReadReplyResponseDTO.of(replyDTO);
                    postReadReplyResponseDTO.setReplies(rereplyDAO.findAll(replyDTO.getId()));
                    return postReadReplyResponseDTO;
                })
                .toList();
    }

    @Override
    public List<Long> findReplyIds(Long postId) {
        return replyDAO.findAllIdByPostId(postId);
    }

    //작성자가 작성한 댓글 수
    public Integer countReply(Long memberId) {
        return replyDAO.countPost(memberId);
    }

    // 댓글 작성
    @Override
    public void writeReply(ReplyCreateRequestDTO replyCreateRequestDTO) {
        replyDAO.save(replyCreateRequestDTO);
    }

    // 댓글 수정
    @Override
    public void updateReply(ReplyUpdateRequestDTO replyUpdateRequestDTO) {
        replyDAO.update(replyUpdateRequestDTO);
    }

    // 댓글 삭제
    @Override
    public void deleteReply(Long replyId) {
        replyLikeDAO.deleteById(replyId);
        rereplyDAO.deleteByRereplyId(replyId);
        replyDAO.delete(replyId);
    }

    //게시글 삭제
    @Override
    public void deleteReplies(Long postId) {
        List<Long> replyIds = findReplyIds(postId);
        replyIds.forEach(this::deleteReply);
    }


}
