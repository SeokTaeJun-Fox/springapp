package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
import com.app.springapp.domain.dto.response.PostLikeResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostLikeMapper {
    public void insert(PostLikeRequestDTO postLikeRequestDTO);
    public PostLikeResponseDTO selectLikeCountAndIsLiked(PostLikeRequestDTO postLikeRequestDTO);
    public void delete(PostLikeRequestDTO postLikeRequestDTO);
}
