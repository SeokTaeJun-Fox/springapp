package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.ReplyLikeRequestDTO;
import com.app.springapp.domain.dto.response.ReplyLikeResponseDTO;
import com.app.springapp.mapper.ReplyLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyLikeDAO {

    private final ReplyLikeMapper replyLikeMapper;

    //댓글 좋아요 기능
    public void save(ReplyLikeRequestDTO replyLikeRequestDTO) {
        replyLikeMapper.insert(replyLikeRequestDTO);
    }

    //댓글 좋아요 갯수, 해당 멤버가 좋아요를 클릭했는지 확인하는 기능
    public Optional<ReplyLikeResponseDTO> findReplyLikeCountAndIsLiked(ReplyLikeRequestDTO replyLikeRequestDTO) {
        return Optional.ofNullable(replyLikeMapper.selectLikeCountAndIsLiked(replyLikeRequestDTO));
    }
}
