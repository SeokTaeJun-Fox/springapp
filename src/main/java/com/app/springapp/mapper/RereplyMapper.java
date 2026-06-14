package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.RereplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.RereplyUpdateRequestDTO;
import com.app.springapp.domain.dto.response.RereplyResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RereplyMapper {
    //댓글id로 대댓글 목록 보여준다
    public List<RereplyResponseDTO> selectAll(Long id);

    //대댓글id로 대댓글 목록 보여준다
    public RereplyResponseDTO select(Long id);

    // 대댓글 작성
    public void insert(RereplyCreateRequestDTO rereplyCreateRequestDTO);

    // 대댓글 수정
    public void updateRereplyContentById(RereplyUpdateRequestDTO rereplyUpdateRequestDTO);

    // 대댓글 삭제
    public void delete(Long id);

    // 대댓글 삭제(댓글 id를 입력값으로 해당 댓글에 달린 대댓글을 전부 삭제한다)
    public void deleteByRereplyId(Long id);
}
