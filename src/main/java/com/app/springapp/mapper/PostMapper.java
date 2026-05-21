package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    public void insert(PostVO postVO);
    public PostVO selectById(Long id);
    public PostResponseDTO selectByMemberIdAndId(PostReadRequestDTO postReadRequestDTO);
}
