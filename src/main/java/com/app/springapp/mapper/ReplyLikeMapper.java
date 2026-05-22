package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.ReplyLikeRequestDTO;
import com.app.springapp.domain.dto.response.ReplyLikeResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyLikeMapper {
    public void insert(ReplyLikeRequestDTO replyLikeRequestDTO);
    public ReplyLikeResponseDTO selectLikeCountAndIsLiked(ReplyLikeRequestDTO replyLikeRequestDTO);
}
