package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.domain.vo.PostVO;
import com.app.springapp.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;

    public PostResponseDTO findByMemberIdAndId(PostReadRequestDTO postReadRequestDTO) {
        return postMapper.selectByMemberIdAndId(postReadRequestDTO);
    }
}
