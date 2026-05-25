package com.app.springapp.mapper;

import com.app.springapp.domain.dto.PostCreateDTO;
import com.app.springapp.domain.dto.request.PostReadRequestDTO;
import com.app.springapp.domain.dto.request.PostUpdateRequestDTO;
import com.app.springapp.domain.dto.response.PostAfterResponseDTO;
import com.app.springapp.domain.dto.response.PostBeforeResponseDTO;
import com.app.springapp.domain.dto.response.PostListResponseDTO;
import com.app.springapp.domain.dto.response.PostResponseDTO;
import com.app.springapp.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    //검색결과 게시글 리스트 반환
    public List<PostListResponseDTO> selectAll(Map<String, Object> order);

    //검색결과 게시글 총갯수 반환
    public Integer CountPost(Map<String, Object> order);

    //게시글 id로 게시글 정보 불러오기 + (memberId로 해당 게시글 좋아요 여부확인 가능)
    //게시글 리스트, 게시글 열람페이지에서 사용된다.
    public PostResponseDTO selectById(PostReadRequestDTO postReadRequestDTO);

    //게시글 id로 게시글 정보 불러오기
    public PostVO select(Long id);

    // POST ID로 이전글 찾기
    public PostBeforeResponseDTO selectBeforePost(Long postId);

    //POST ID로 다음글 찾기
    public PostAfterResponseDTO selectAfterPost(Long postId);

    //작성자가 작성한 게시글 수
    public Integer selectPostCountByMemberId(Long memberId);

    //게시글 작성
    public void insert(PostCreateDTO postCreateDTO);

    //게시글 수정
    public void update(PostUpdateRequestDTO postUpdateRequestDTO);

    //게시글 조회수 증가
    public void updatePostReadCount(Long id);

    //게시글 삭제
    public void delete(Long id);
}
