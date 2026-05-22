package com.app.springapp.repository;

import com.app.springapp.domain.dto.ReplyDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {

    private final ReplyMapper replyMapper;

    // 게시글id와 멤버id로 댓글 정보 목록 불러오기
    public List<ReplyDTO> findAll(PostReadRequestDTO postReadRequestDTO) {
        return replyMapper.selectAll(postReadRequestDTO);
    }

    //작성자가 작성한 댓글 수
    public Integer countPost(Long memberId) {
        return replyMapper.selectReplyCountByMemberId(memberId);
    }
}
