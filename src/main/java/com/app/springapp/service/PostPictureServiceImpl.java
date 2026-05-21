package com.app.springapp.service;

import com.app.springapp.repository.PostPictureDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PostPictureServiceImpl implements PostPictureService {

    private final PostPictureDAO postPictureDAO;

    //게시글 id로 첨부 이미지 주소목록(배열) 불러오기
    @Override
    public List<String> findAll(Long postId) {
        return postPictureDAO.findAll(postId);
    }
}
