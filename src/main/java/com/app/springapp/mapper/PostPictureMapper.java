package com.app.springapp.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostPictureMapper {
    //게시글 id로 첨부 이미지 주소목록 불러오기
    public List<String> selectAll(Long id);
}
