package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.PostLikeRequestDTO;
import com.app.springapp.domain.dto.response.PostLikeResponseDTO;
import com.app.springapp.mapper.PostLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostLikeDAO {

    private final PostLikeMapper postLikeMapper;

    //게시글 좋아요 기능
    public void save(PostLikeRequestDTO postLikeRequestDTO) {
        postLikeMapper.insert(postLikeRequestDTO);
    }

    //게시글 좋아요 갯수, 해당 멤버가 좋아요를 클릭했는지 확인하는 기능
    public Optional<PostLikeResponseDTO> findPostLikeCountAndIsLiked(PostLikeRequestDTO postLikeRequestDTO) {
        return Optional.ofNullable(postLikeMapper.selectLikeCountAndIsLiked(postLikeRequestDTO));
    }

    public void delete(PostLikeRequestDTO postLikeRequestDTO) {
        postLikeMapper.delete(postLikeRequestDTO);
    }
}
