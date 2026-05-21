package com.app.springapp.repository;

import com.app.springapp.mapper.PostPictureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostPictureDAO {

    private final PostPictureMapper postPictureMapper;

    //게시글 id로 첨부 이미지 주소목록 불러오기
    public List<String> findAll(Long postId) {
        return postPictureMapper.selectAll(postId);
    }
}
