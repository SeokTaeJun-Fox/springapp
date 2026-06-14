package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.RereplyCreateRequestDTO;
import com.app.springapp.domain.dto.request.RereplyUpdateRequestDTO;
import com.app.springapp.domain.dto.response.RereplyResponseDTO;
import com.app.springapp.mapper.RereplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RereplyDAO {

    private final RereplyMapper rereplyMapper;

    //댓글id로 대댓글 목록 보여준다
    public List<RereplyResponseDTO> findAll(Long id) {
        return rereplyMapper.selectAll(id);
    }

    //대댓글id로 대댓글 목록 보여준다
    public RereplyResponseDTO find(Long id) {
        return rereplyMapper.select(id);
    }

    //대댓글 작성
    public void save(RereplyCreateRequestDTO rereplyCreateRequestDTO) {
        rereplyMapper.insert(rereplyCreateRequestDTO);
    }

    public void update(RereplyUpdateRequestDTO rereplyUpdateRequestDTO) {
        rereplyMapper.updateRereplyContentById(rereplyUpdateRequestDTO);
    }

    //대댓글 삭제
    public void delete(Long rereplyId) {
        rereplyMapper.delete(rereplyId);
    }

    // 대댓글 삭제(댓글 id를 입력값으로 해당 댓글에 달린 대댓글을 전부 삭제한다)
    public void deleteByRereplyId(Long replyId) {
        rereplyMapper.deleteByRereplyId(replyId);
    }
}
