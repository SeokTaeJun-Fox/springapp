package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.domain.vo.PostVO;
import com.app.springapp.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    public Optional<PostResponseDTO> findById(PostReadRequestDTO postReadRequestDTO) {
        return Optional.ofNullable(postMapper.selectById(postReadRequestDTO));
    }
}
