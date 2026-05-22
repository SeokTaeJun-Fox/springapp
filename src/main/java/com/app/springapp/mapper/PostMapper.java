package com.app.springapp.mapper;

import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.response.PostAfterResponseDTO;
import com.app.springapp.domain.dto.response.PostBeforeResponseDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import retrofit2.http.POST;

import java.util.Optional;

@Mapper
public interface PostMapper {
    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    public PostResponseDTO selectById(PostReadRequestDTO postReadRequestDTO);

    // POST ID로 이전글 찾기
    public PostBeforeResponseDTO selectBeforePost(Long postId);

    //POST ID로 다음글 찾기
    public PostAfterResponseDTO selectAfterPost(Long postId);

    //작성자가 작성한 게시글 수
    public Integer selectPostCountByMemberId(Long memberId);
}
