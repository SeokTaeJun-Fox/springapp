package com.app.springapp.mapper;

import com.app.springapp.domain.dto.ReplyDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    // 게시글id와 멤버id로 댓글 정보 목록 불러오기
    public List<ReplyDTO> selectAll(PostReadRequestDTO postReadRequestDTO);

    //작성자가 작성한 댓글 수
    public Integer selectReplyCountByMemberId(Long memberId);
}
