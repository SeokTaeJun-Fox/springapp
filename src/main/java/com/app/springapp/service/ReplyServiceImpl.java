package com.app.springapp.service;

import com.app.springapp.domain.dto.ReplyDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostReadReplyResponseDTO;
import com.app.springapp.repository.ReplyDAO;
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

    //작성자가 작성한 댓글 수
    public Integer countReply(Long memberId) {
        return replyDAO.countPost(memberId);
    }
}
