package com.app.springapp.mapper;

import com.app.springapp.domain.dto.ReplyDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.ReplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.ReplyReadRequestDTO;
import com.app.springapp.domain.dto.request.ReplyUpdateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    // 게시글id와 멤버id로 댓글 정보 목록 불러오기
    public List<ReplyDTO> selectAll(PostReadRequestDTO postReadRequestDTO);

    // 댓글id와 멤버id로 댓글 정보 목록 불러오기
    public ReplyDTO select(ReplyReadRequestDTO replyReadRequestDTO);

    //게시글 id로 모든 댓글id 불러오기
    public List<Long> selectAllIdByPostId(Long id);

    //작성자가 작성한 댓글 수
    public Integer selectReplyCountByMemberId(Long memberId);

    //댓글 작성
    public void insert(ReplyCreateRequestDTO replyCreateRequestDTO);

    //댓글 수정
    public void updateReplyContentById(ReplyUpdateRequestDTO replyUpdateRequestDTO);

    //댓글 삭제
    public void delete(Long id);
}
