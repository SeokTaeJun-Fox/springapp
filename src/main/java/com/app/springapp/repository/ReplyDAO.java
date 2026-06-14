package com.app.springapp.repository;

import com.app.springapp.domain.dto.ReplyDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.ReplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.ReplyReadRequestDTO;
import com.app.springapp.domain.dto.request.ReplyUpdateRequestDTO;
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

    // 댓글id와 멤버id로 댓글 정보 불러오기
    public ReplyDTO find(ReplyReadRequestDTO replyReadRequestDTO) {
        return replyMapper.select(replyReadRequestDTO);
    }

    //게시글 id로 모든 댓글id 불러오기
    public List<Long> findAllIdByPostId(Long id) {
        return replyMapper.selectAllIdByPostId(id);
    }

    // 작성자가 작성한 댓글 수
    public Integer countPost(Long memberId) {
        return replyMapper.selectReplyCountByMemberId(memberId);
    }

    // 댓글 작성
    public void save(ReplyCreateRequestDTO replyCreateRequestDTO) {
        replyMapper.insert(replyCreateRequestDTO);
    }

    // 댓글 수정
    public void update(ReplyUpdateRequestDTO replyUpdateRequestDTO) {
        replyMapper.updateReplyContentById(replyUpdateRequestDTO);
    }

    // 댓글 삭제
    public void delete(Long replyId) {
        replyMapper.delete(replyId);
    }
}
