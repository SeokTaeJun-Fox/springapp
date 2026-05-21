package com.app.springapp.service;

import java.util.List;

public interface PostPictureService {
    //게시글 id로 첨부 이미지 주소목록 불러오기
    public List<String> findAll(Long postId);
}
